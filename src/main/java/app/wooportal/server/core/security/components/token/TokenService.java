package app.wooportal.server.core.security.components.token;

import java.util.Date;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import app.wooportal.server.core.error.exception.InvalidTokenException;
import app.wooportal.server.core.security.JwtConfiguration;
import app.wooportal.server.core.security.JwtUserDetails;

@Service
public class TokenService {

  private JwtConfiguration securityConfig;

  public TokenService(JwtConfiguration securityConfig) throws Exception {
    this.securityConfig = securityConfig;
  }

  public String createAccessToken(JwtUserDetails jwtUserDetails) {
    return JWT.create().withSubject(jwtUserDetails.getUsername())
        .withClaim(securityConfig.getClaimUserid(), jwtUserDetails.getUser().getId())
        .withArrayClaim(securityConfig.getClaimRoles(), jwtUserDetails.getRoles())
        .withArrayClaim(securityConfig.getClaimScopes(),
            new String[] {securityConfig.getScopeAccess()})
        .withExpiresAt(new Date(
            System.currentTimeMillis() + securityConfig.getExpirationTimeAccess().toMillis()))
        .sign(Algorithm.HMAC512(securityConfig.getSecret()));
  }

  public String createRefreshToken(JwtUserDetails jwtUserDetails) {
    return JWT.create().withSubject(jwtUserDetails.getUsername())
        .withArrayClaim(securityConfig.getClaimScopes(),
            new String[] {securityConfig.getScopeRefresh()})
        .withExpiresAt(new Date(
            System.currentTimeMillis() + securityConfig.getExpirationTimeRefresh().toMillis()))
        .sign(Algorithm.HMAC512(securityConfig.getSecret()));
  }

  public DecodedJWT verifyAccess(String jwtToken) throws InvalidTokenException {
    return verifyWithScope(jwtToken, securityConfig.getScopeAccess());
  }

  public DecodedJWT verifyRefresh(String token) throws InvalidTokenException {
    return verifyWithScope(token, securityConfig.getScopeRefresh());
  }

  private DecodedJWT verifyWithScope(String token, String requiredScope) {
    var decodedToken = verify(token);
    if (decodedToken.getClaim(securityConfig.getClaimScopes()).asList(String.class)
        .contains(requiredScope)) {
      return decodedToken;
    }
    throw new InvalidTokenException("Token must contain scope", requiredScope);
  }

  public DecodedJWT verify(String token) {
    return JWT.require(Algorithm.HMAC512(securityConfig.getSecret())).build()
        .verify(token.replace("Bearer ", ""));
  }

}
