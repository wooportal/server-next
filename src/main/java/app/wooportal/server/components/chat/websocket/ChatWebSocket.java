package app.wooportal.server.components.chat.websocket;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import app.wooportal.server.components.chat.WebsocketMessage;
import app.wooportal.server.components.chat.XMPPFacade;
import app.wooportal.server.components.chat.websocket.util.MessageDecoder;
import app.wooportal.server.components.chat.websocket.util.MessageEncoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ServerEndpoint(value = "/chat/{username}/{password}", decoders = MessageDecoder.class,
    encoders = MessageEncoder.class)
public class ChatWebSocket {

  @Autowired
  private XMPPFacade xmppFacade;

  @OnOpen
  public void open(Session session, @PathParam("username") String username,
      @PathParam("password") String password) {
    xmppFacade.startSession(session, username, password);
  }

  @OnMessage
  public void handleMessage(WebsocketMessage message, Session session) {
    xmppFacade.sendMessage(message, session);
  }

  @OnClose
  public void close(Session session) {
    xmppFacade.disconnect(session);
  }

  @OnError
  public void onError(Throwable e, Session session) {
    log.debug(e.getMessage());
    xmppFacade.disconnect(session);
  }
}
