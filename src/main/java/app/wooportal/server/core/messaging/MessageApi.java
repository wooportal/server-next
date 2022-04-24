package app.wooportal.server.core.messaging;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.jivesoftware.smack.roster.RosterEntry;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;
import app.wooportal.server.core.error.exception.InvalidTokenException;
import app.wooportal.server.core.error.exception.XmppException;
import app.wooportal.server.core.security.services.AuthorizationService;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLSubscription;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Component
@GraphQLApi
public class MessageApi {
  
  private final AuthorizationService authService;
  
  private final XmppService xmppService;
  
  private static final Map<String, XMPPTCPConnection> CONNECTIONS2 = new HashMap<>();
  
  public MessageApi(
      AuthorizationService authService,
      XmppService xmppClient) {
    this.authService = authService;
    this.xmppService = xmppClient;
  }

  @GraphQLSubscription
  public Publisher<MessageDto> incomingMessages(String token) {
    var user = authService.getValidUserFromToken(token);
    if (user.isEmpty()) {
      throw new InvalidTokenException("Invalid token, either user doesn't exist or token invalid", token);
    }
    return xmppService.addMessageListener(user.get());
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
                  xmppService.sendMessage(connection, message.getContent(), message.getTo());
                  // TODO: save message for both users in DB
              } catch (XmppException e) {
              }
          }
          case ADD_CONTACT -> {
              try {
                  xmppService.addContact(connection, message.getTo());
              } catch (XmppException e) {
              }
          }
          case GET_CONTACTS -> {
              Set<RosterEntry> contacts = Set.of();
              try {
                  contacts = xmppService.getContacts(connection);
              } catch (XmppException e) {
              }

//              JSONArray jsonArray = new JSONArray();
//              for (RosterEntry entry : contacts) {
//                  jsonArray.put(entry.getName());
//              }
//              WebsocketMessage responseMessage = WebsocketMessage.builder()
//                      .content(jsonArray.toString())
//                      .messageType(GET_CONTACTS)
//                      .build();
//              log.info("Returning list of contacts {} for user {}.", jsonArray, connection.getUser());
//              webSocketTextMessageHelper.send(session, responseMessage);
          }
        default -> throw new IllegalArgumentException("Unexpected value: " + message.getMessageType());
      }
  }
  
}
