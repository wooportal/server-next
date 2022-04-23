package app.wooportal.server.core.security.services;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.security.JwtUserDetails;
import app.wooportal.server.core.security.components.user.UserEntity;

@Service
public class AuthorizationService {
  
  @Autowired
  protected HttpServletRequest request;
  

  public UserEntity getCurrentUser() {
    if (SecurityContextHolder.getContext().getAuthentication()
        .getPrincipal() instanceof JwtUserDetails) {
      JwtUserDetails jwtUserDetails =
          (JwtUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      if (jwtUserDetails != null && jwtUserDetails.getUser() != null) {
        return jwtUserDetails.getUser();
      }
    }
    return null;
  }

}
