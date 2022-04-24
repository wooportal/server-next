package app.wooportal.server.core.security.services;

import java.util.Collections;
import java.util.Optional;
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

  public Optional<UserEntity> getUserFromSecurityContext() {
    if (SecurityContextHolder.getContext().getAuthentication()
        .getPrincipal() instanceof JwtUserDetails) {
      JwtUserDetails jwtUserDetails =
          (JwtUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      if (jwtUserDetails != null && jwtUserDetails.getUser() != null) {
        return Optional.of(jwtUserDetails.getUser());
      }
    }
    return Optional.empty();
  }
  
  public Optional<UserEntity> getValidUserFromToken(String jwtToken) {
    var userDetails = retrieveUserDetailsFromToken(jwtToken);
    return userDetails.isPresent()
      ? Optional.ofNullable(userDetails.get().getUser())
      : Optional.empty();
  }

  public Optional<UsernamePasswordAuthenticationToken> getAuthenticationToken(HttpServletRequest request) {
    if (request != null) {
      try {
        String jwtToken = request.getHeader("Authorization");
        var userDetails = retrieveUserDetailsFromToken(jwtToken);
        if (userDetails.isPresent()) {
          return Optional.of(new UsernamePasswordAuthenticationToken(
              userDetails.get(), null, Collections.emptyList())); 
        }
      } catch (Exception ignored) { }
    }
    return null;
  }

  private Optional<JwtUserDetails> retrieveUserDetailsFromToken(String jwtToken) {
    if (jwtToken != null) {
      String username = tokenService.verifyAccess(jwtToken).getSubject();
      if (username != null) {
        return Optional.ofNullable(userDetailsService.loadUserByUsername(username));
      }
    }
    return Optional.empty();
  }

}
