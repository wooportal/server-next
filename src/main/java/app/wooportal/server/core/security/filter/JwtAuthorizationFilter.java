package app.wooportal.server.core.security.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import app.wooportal.server.core.security.services.AuthorizationService;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

  private final AuthorizationService authService;
  
  public JwtAuthorizationFilter(
      AuthenticationManager authManager,
      AuthorizationService authService) {
    super(authManager);
    this.authService = authService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
      FilterChain chain) throws IOException, ServletException {

    String header = req.getHeader("Authorization");
    if (header == null || !header.startsWith("Bearer ")) {
      chain.doFilter(req, res);
      return;
    }

    UsernamePasswordAuthenticationToken authentication = authService.getAuthentication(req);
    
    if (authentication != null) {
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    chain.doFilter(req, res);
  }
}
