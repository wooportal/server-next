package app.wooportal.server.components.chat.websocket.util;

import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import app.wooportal.server.components.chat.WebsocketMessage;

public class MessageDecoder implements Decoder.Text<WebsocketMessage> {

  @Override
  public WebsocketMessage decode(String message) {
    // Gson gson = new Gson();
    // return gson.fromJson(message, WebsocketMessage.class);
    return null;
  }

  @Override
  public boolean willDecode(String message) {
    return (message != null);
  }

  @Override
  public void init(EndpointConfig config) {

  }

  @Override
  public void destroy() {

  }
}
