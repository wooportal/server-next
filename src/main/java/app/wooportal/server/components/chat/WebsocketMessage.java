package app.wooportal.server.components.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WebsocketMessage {
    String from;
    String to;
    String content;
    MessageType messageType;
}
