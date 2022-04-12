package app.wooportal.server.components.chat.websocket.util;


import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import app.wooportal.server.components.chat.WebsocketMessage;

public class MessageEncoder implements Encoder.Text<WebsocketMessage> {
  @Override
  public String encode(WebsocketMessage message) {
    return null;
  }

  @Override
  public void init(EndpointConfig config) {

  }

  @Override
  public void destroy() {

  }
}
