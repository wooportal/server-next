package app.wooportal.server.components.chat;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import javax.websocket.Session;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;
import app.wooportal.server.components.chat.websocket.util.WebSocketTextMessageHelper;
import app.wooportal.server.components.chat.xmpp.MessageDto;
import app.wooportal.server.components.chat.xmpp.XMPPClient;
import app.wooportal.server.core.security.components.user.UserService;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.GraphQLSubscription;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@GraphQLApi
public class XMPPFacade {

    private static final Map<Session, XMPPTCPConnection> CONNECTIONS = new HashMap<>();
    
    private static final Map<String, XMPPTCPConnection> CONNECTIONS2 = new HashMap<>();

    private final UserService accountService;
    private final WebSocketTextMessageHelper webSocketTextMessageHelper;
    private final XMPPClient xmppClient;
    
    @GraphQLQuery
    public String test() {
      return "hello";
    }

    @GraphQLSubscription
    public Publisher<MessageDto> taskStatusChanged(String username, String password) {
      var account = accountService.getByLoginName(username);

      Optional<XMPPTCPConnection> connection = xmppClient.connect(username, password);

      try {
          if (account.isEmpty()) {
              xmppClient.createAccount(connection.get(), username, password);
          }
          xmppClient.login(connection.get());
      } catch (XMPPGenericException e) {
      }
      
      log.info("Session was stored.");
      CONNECTIONS2.put(username, connection.get());
      return xmppClient.addIncomingMessageListener(connection.get());
    }
    
    
    public void startSession(Session session, String username, String password) {
        // TODO: Save user session to avoid having to login again when the websocket connection is closed
        //      1. Generate token
        //      2. Save username and token in Redis
        //      3. Return token to client and store it in a cookie or local storage
        //      4. When starting a websocket session check if the token is still valid and bypass XMPP authentication
        Optional<Account> account = accountService.getAccount(username);

        if (account.isPresent() && !BCryptUtils.isMatch(password, account.get().getPassword())) {
            log.warn("Invalid password for user {}.", username);
            
            webSocketTextMessageHelper.send(session, WebsocketMessage.builder().messageType(FORBIDDEN).build());
            return;
        }

        Optional<XMPPTCPConnection> connection = xmppClient.connect(username, password);

        if (connection.isEmpty()) {
            webSocketTextMessageHelper.send(session, WebsocketMessage.builder().messageType(ERROR).build());
            return;
        }

        try {
            if (account.isEmpty()) {
                xmppClient.createAccount(connection.get(), username, password);
            }
            xmppClient.login(connection.get());
        } catch (XMPPGenericException e) {
            handleXMPPGenericException(session, connection.get(), e);
            return;
        }

        CONNECTIONS.put(session, connection.get());
        log.info("Session was stored.");

        xmppClient.addIncomingMessageListener(connection.get(), session);

        webSocketTextMessageHelper.send(session, WebsocketMessage.builder().to(username).messageType(JOIN_SUCCESS).build());
    }
    
    @GraphQLMutation
    public void sendMessage(WebsocketMessage message) {
        XMPPTCPConnection connection = CONNECTIONS2.get(message.getFrom());

        if (connection == null) {
            return;
        }

        switch (message.getMessageType()) {
            case NEW_MESSAGE -> {
                try {
                    xmppClient.sendMessage(connection, message.getContent(), message.getTo());
                    // TODO: save message for both users in DB
                } catch (XMPPGenericException e) {
                }
            }
            case ADD_CONTACT -> {
                try {
                    xmppClient.addContact(connection, message.getTo());
                } catch (XMPPGenericException e) {
                }
            }
            case GET_CONTACTS -> {
                Set<RosterEntry> contacts = Set.of();
                try {
                    contacts = xmppClient.getContacts(connection);
                } catch (XMPPGenericException e) {
                }

                JSONArray jsonArray = new JSONArray();
                for (RosterEntry entry : contacts) {
                    jsonArray.put(entry.getName());
                }
//                WebsocketMessage responseMessage = WebsocketMessage.builder()
//                        .content(jsonArray.toString())
//                        .messageType(GET_CONTACTS)
//                        .build();
//                log.info("Returning list of contacts {} for user {}.", jsonArray, connection.getUser());
//                webSocketTextMessageHelper.send(session, responseMessage);
            }
            default -> log.warn("Message type not implemented.");
        }
    }

    public void sendMessage(WebsocketMessage message, Session session) {
        XMPPTCPConnection connection = CONNECTIONS.get(session);

        if (connection == null) {
            return;
        }

        switch (message.getMessageType()) {
            case NEW_MESSAGE -> {
                try {
                    xmppClient.sendMessage(connection, message.getContent(), message.getTo());
                    // TODO: save message for both users in DB
                } catch (XMPPGenericException e) {
                    handleXMPPGenericException(session, connection, e);
                }
            }
            case ADD_CONTACT -> {
                try {
                    xmppClient.addContact(connection, message.getTo());
                } catch (XMPPGenericException e) {
                    handleXMPPGenericException(session, connection, e);
                }
            }
            case GET_CONTACTS -> {
                Set<RosterEntry> contacts = Set.of();
                try {
                    contacts = xmppClient.getContacts(connection);
                } catch (XMPPGenericException e) {
                    handleXMPPGenericException(session, connection, e);
                }

                JSONArray jsonArray = new JSONArray();
                for (RosterEntry entry : contacts) {
                    jsonArray.put(entry.getName());
                }
                WebsocketMessage responseMessage = WebsocketMessage.builder()
                        .content(jsonArray.toString())
                        .messageType(GET_CONTACTS)
                        .build();
                log.info("Returning list of contacts {} for user {}.", jsonArray, connection.getUser());
                webSocketTextMessageHelper.send(session, responseMessage);
            }
            default -> log.warn("Message type not implemented.");
        }
    }

    public void disconnect(Session session) {
        XMPPTCPConnection connection = CONNECTIONS.get(session);

        if (connection == null) {
            return;
        }

        try {
            xmppClient.sendStanza(connection, Presence.Type.unavailable);
        } catch (XMPPGenericException e) {
            log.error("XMPP error.", e);
            webSocketTextMessageHelper.send(session, WebsocketMessage.builder().messageType(ERROR).build());
        }

        xmppClient.disconnect(connection);
        CONNECTIONS.remove(session);
    }

    private void handleXMPPGenericException(Session session, XMPPTCPConnection connection, Exception e) {
        log.error("XMPP error. Disconnecting and removing session...", e);
        xmppClient.disconnect(connection);
        webSocketTextMessageHelper.send(session, WebsocketMessage.builder().messageType(ERROR).build());
        CONNECTIONS.remove(session);
    }
}
