package app.wooportal.server.components.chat.xmpp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
  
  private String to;
  
  private String message;

}
