package app.wooportal.server.core.security.services;

import java.util.Collections;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.security.JwtUserDetails;
import app.wooportal.server.core.security.components.token.TokenService;
import app.wooportal.server.core.security.components.user.UserEntity;

@Service
public class AuthorizationService {

  private final TokenService tokenService;

  private final JwtUserDetailsService userDetailsService;

  public AuthorizationService(TokenService tokenService, JwtUserDetailsService userDetailsService) {
    this.tokenService = tokenService;
    this.userDetailsService = userDetailsService;
  }


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

  public UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
    if (request != null) {
      try {
        String jwtToken = request.getHeader("Authorization");
        if (jwtToken != null) {
          String username = tokenService.verifyAccess(jwtToken).getSubject();
          if (username != null) {
            var userDetails = userDetailsService.loadUserByUsername(username);
            if (userDetails != null) {
              return new UsernamePasswordAuthenticationToken(
                  userDetails, null, Collections.emptyList()); 
            }
          }
        }
      } catch (Exception ignored) { }
    }
    return null;
  }

}
