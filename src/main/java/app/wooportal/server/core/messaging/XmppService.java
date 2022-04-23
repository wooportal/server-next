package app.wooportal.server.core.messaging;

import java.io.IOException;
import java.util.Set;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.chat2.Chat;
import org.jivesoftware.smack.chat2.ChatManager;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.PresenceBuilder;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.roster.RosterEntry;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smackx.iqregister.AccountManager;
import org.jxmpp.jid.BareJid;
import org.jxmpp.jid.EntityFullJid;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.jid.parts.Localpart;
import org.jxmpp.stringprep.XmppStringprepException;
import org.reactivestreams.Publisher;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import app.wooportal.server.core.error.exception.XmppException;
import app.wooportal.server.core.security.components.user.UserEntity;
import app.wooportal.server.core.security.services.AuthorizationService;
import io.leangen.graphql.spqr.spring.util.ConcurrentMultiMap;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(XMPPProperties.class)
public class XmppService {
  
  private final AuthorizationService authService;
  private final XMPPProperties xmppProperties;
  private final XMPPMessageTransmitter xmppMessageTransmitter;
  private final ConcurrentMultiMap<String, FluxSink<MessageDto>> subscribers = new ConcurrentMultiMap<>();

  private XMPPTCPConnection connect(UserEntity user) {
    try {
      var entityBareJid = JidCreate.entityBareFrom(user.getId() + "@" + xmppProperties.getDomain());
      var connection = new XMPPTCPConnection(
          XMPPTCPConnectionConfiguration.builder().setHost(xmppProperties.getDomain())
          .setPort(xmppProperties.getPort()).setXmppDomain(xmppProperties.getDomain())
          .setUsernameAndPassword(entityBareJid.getLocalpart(), user.getPassword())
          .setSecurityMode(ConnectionConfiguration.SecurityMode.disabled)
          .setResource(entityBareJid.getResourceOrEmpty()).setSendPresence(true).build());
      connection.connect();
      return connection;
    } catch (IOException | SmackException | XMPPException | InterruptedException e) {
      throw new XmppException(e.getMessage(), user);
    }
  }

  public void createAccount(UserEntity user) {
    AccountManager accountManager = AccountManager.getInstance(connect(user));
    accountManager.sensitiveOperationOverInsecureConnection(true);
    try {
      accountManager.createAccount(Localpart.from(user.getId()), user.getPassword());
    } catch (SmackException.NoResponseException | XMPPException.XMPPErrorException
        | SmackException.NotConnectedException | InterruptedException | XmppStringprepException e) {
      throw new XmppException(e.getMessage(), user);
    }
  }

  public XMPPTCPConnection login(UserEntity user) {
    try {
      var connection = connect(user);
      connection.login();
      return connection;
    } catch (XMPPException | SmackException | IOException | InterruptedException e) {
      throw new XmppException(e.getMessage(), user);
    }
  }

  public Publisher<MessageDto> addMessageListener() {
    var connection = login(authService.getCurrentUser());
    return Flux.create(
        subscriber -> subscribers.add(connection.getUser().getLocalpart().toString(),
            subscriber.onDispose(() -> subscribers
                .remove(connection.getUser().getLocalpart().toString(), subscriber))),
        FluxSink.OverflowStrategy.LATEST);
  }

  public void sendMessage(XMPPTCPConnection connection, String message, String to) {
    ChatManager chatManager = ChatManager.getInstanceFor(connection);
    try {
      Chat chat =
          chatManager.chatWith(JidCreate.entityBareFrom(to + "@" + xmppProperties.getDomain()));
      chat.send(message);
      subscribers.get(connection.getUser().getLocalpart().toString())
          .forEach(subscriber -> subscriber.next(new MessageDto(to, message)));

      subscribers.get(to).forEach(subscriber -> subscriber
          .next(new MessageDto(connection.getUser().getLocalpart().toString(), message)));
    } catch (XmppStringprepException | SmackException.NotConnectedException
        | InterruptedException e) {
      throw new XmppException(connection.getUser().toString(), e);
    }
  }

  public void addContact(XMPPTCPConnection connection, String to) {
    Roster roster = Roster.getInstanceFor(connection);
    if (!roster.isLoaded()) {
      try {
        roster.reloadAndWait();
      } catch (SmackException.NotLoggedInException | SmackException.NotConnectedException
          | InterruptedException e) {
        throw new XmppException(connection.getUser().toString(), e);
      }
    }

    try {
      BareJid contact = JidCreate.bareFrom(to + "@" + xmppProperties.getDomain());
      roster.createItemAndRequestSubscription(contact, to, null);
    } catch (XmppStringprepException | XMPPException.XMPPErrorException
        | SmackException.NotConnectedException | SmackException.NoResponseException
        | SmackException.NotLoggedInException | InterruptedException e) {
      throw new XmppException(connection.getUser().toString(), e);
    }
  }

  public Set<RosterEntry> getContacts(XMPPTCPConnection connection) {
    Roster roster = Roster.getInstanceFor(connection);

    if (!roster.isLoaded()) {
      try {
        roster.reloadAndWait();
      } catch (SmackException.NotLoggedInException | SmackException.NotConnectedException
          | InterruptedException e) {
        throw new XmppException(connection.getUser().toString(), e);
      }
    }

    return roster.getEntries();
  }

  public void disconnect(XMPPTCPConnection connection) {
    Presence presence = PresenceBuilder.buildPresence().ofType(Presence.Type.unavailable).build();
    try {
      connection.sendStanza(presence);
    } catch (SmackException.NotConnectedException | InterruptedException e) {

    }
    connection.disconnect();
  }

  public void sendStanza(XMPPTCPConnection connection, Presence.Type type) {
    Presence presence = PresenceBuilder.buildPresence().ofType(type).build();
    try {
      connection.sendStanza(presence);
    } catch (SmackException.NotConnectedException | InterruptedException e) {
      throw new XmppException(connection.getUser().toString(), e);
    }
  }
}
