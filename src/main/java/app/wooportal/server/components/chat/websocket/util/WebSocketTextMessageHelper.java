package app.wooportal.server.components.chat.websocket.util;

import java.io.IOException;
import javax.websocket.EncodeException;
import javax.websocket.Session;
import org.springframework.stereotype.Component;
import app.wooportal.server.components.chat.WebsocketMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class WebSocketTextMessageHelper {

  public void send(Session session, WebsocketMessage websocketMessage) {
    try {
      session.getBasicRemote().sendObject(websocketMessage);
    } catch (IOException | EncodeException e) {
      log.error("WebSocket error, message {} was not sent.", websocketMessage.toString(), e);
    }
  }
}
