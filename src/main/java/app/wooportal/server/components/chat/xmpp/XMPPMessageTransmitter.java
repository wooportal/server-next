package app.wooportal.server.components.chat.xmpp;

import javax.websocket.Session;
import org.jivesoftware.smack.packet.Message;
import org.springframework.stereotype.Component;
import app.wooportal.server.components.chat.MessageType;
import app.wooportal.server.components.chat.WebsocketMessage;
import app.wooportal.server.components.chat.websocket.util.WebSocketTextMessageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class XMPPMessageTransmitter {

    private final WebSocketTextMessageHelper webSocketTextMessageHelper;

    public void sendResponse(Message message, Session session) {
        log.info("New message from '{}' to '{}': {}", message.getFrom(), message.getTo(), message.getBody());
        String messageFrom = message.getFrom().getLocalpartOrNull().toString();
        String to = message.getTo().getLocalpartOrNull().toString();
        String content = message.getBody();
        webSocketTextMessageHelper.send(
                session,
                WebsocketMessage.builder()
                        .from(messageFrom)
                        .to(to)
                        .content(content)
                        .messageType(MessageType.NEW_MESSAGE).build()
        );
    }
}
