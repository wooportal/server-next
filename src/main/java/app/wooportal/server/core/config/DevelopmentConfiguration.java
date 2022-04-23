package app.wooportal.server.core.config;

import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.messaging.XmppService;
import app.wooportal.server.core.security.components.user.UserService;

@Profile(value = { "development", "local" })
@Service
public class DevelopmentConfiguration {

   private final UserService userService;
   
   private final XmppService xmppService;
   
   public DevelopmentConfiguration(
       UserService userService,
       XmppService xmppService) {
     this.userService = userService;
     this.xmppService = xmppService;
   }
   
//   @PostConstruct
//   public void postConstruct() {
//     var result = userService.getRepo().findAll();
//     
//     if (result != null && !result.isEmpty()) {
//       for (var entity : result) {
//         try {
//           xmppService.login(entity);
//         } catch(Exception e) {
//           
//         }
//       }
//     }
//   }
}
