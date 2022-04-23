package app.wooportal.server.core.messaging;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "xmpp")
public class XMPPProperties {

    private String host;

    private int port;

    private String domain;

}
