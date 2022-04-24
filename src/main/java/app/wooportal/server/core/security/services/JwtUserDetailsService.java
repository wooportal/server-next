package app.wooportal.server.core.security.services;

import app.wooportal.server.core.security.JwtUserDetails;
import app.wooportal.server.core.security.components.role.RoleService;
import app.wooportal.server.core.security.components.user.UserEntity;
import app.wooportal.server.core.security.components.user.UserService;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

  private final UserService userService;

  private final RoleService roleService;

  public JwtUserDetailsService(UserService userService, RoleService roleService) {
    this.userService = userService;
    this.roleService = roleService;
  }

  @Override
  public JwtUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<UserEntity> user = userService.getByLoginName(username);

    return user.isPresent() 
        ? new JwtUserDetails(user.get(), roleService.getByUser(user.get()))
        : null;
  }

}
