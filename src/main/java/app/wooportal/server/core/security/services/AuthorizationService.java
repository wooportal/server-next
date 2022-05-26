package app.wooportal.server.core.security.services;

import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.security.JwtUserDetails;
import app.wooportal.server.core.security.components.user.UserEntity;

@Service
public class AuthorizationService {

  public Optional<UserEntity> getCurrentUser() {
    var authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication.getPrincipal() instanceof JwtUserDetails) {
      var jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
      return Optional.of(jwtUserDetails.getUser());
    }
    return Optional.empty();
  }

  public boolean isAdmin(Authentication authentication) {
    if (authentication.getPrincipal() instanceof JwtUserDetails) {
      var jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
      return jwtUserDetails.isAdmin();
    }
    return false;
  }

  public boolean isApproved(Authentication authentication) {
    if (authentication.getPrincipal() instanceof JwtUserDetails) {
      var jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
      return jwtUserDetails.isVerified();
    }
    return false;
  }

  public boolean isSuperviser(Authentication authentication) {
    if (authentication.getPrincipal() instanceof JwtUserDetails) {
      var jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
      return jwtUserDetails.isSuperviser();
    }
    return false;
  }

  public boolean isVerified(Authentication authentication) {
    if (authentication.getPrincipal() instanceof JwtUserDetails) {
      var jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
      return jwtUserDetails.isVerified();
    }
    return false;
  }

}
