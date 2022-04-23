package app.wooportal.server.core.messaging;

import javax.websocket.Session;
import org.jivesoftware.smack.packet.Message;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class XMPPMessageTransmitter {

//    private final WebSocketTextMessageHelper webSocketTextMessageHelper;

    public void sendResponse(Message message, Session session) {
//        log.info("New message from '{}' to '{}': {}", message.getFrom(), message.getTo(), message.getBody());
//        String messageFrom = message.getFrom().getLocalpartOrNull().toString();
//        String to = message.getTo().getLocalpartOrNull().toString();
//        String content = message.getBody();
//        webSocketTextMessageHelper.send(
//                session,
//                WebsocketMessage.builder()
//                        .from(messageFrom)
//                        .to(to)
//                        .content(content)
//                        .messageType(NEW_MESSAGE).build()
//        );
    }
}
