/* (C) 2024 */
package aladdinsys.lifelong_learning_survey.global.security;

import static aladdinsys.lifelong_learning_survey.global.constant.ErrorCode.*;

import aladdinsys.lifelong_learning_survey.global.exception.CustomException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class JwtProvider {

  @Value("${jwt.access-token.secret}")
  private String accessSecretKey;

  @Value("${jwt.access-token.expiration}")
  private long accessExpiration;

  @Value("${jwt.refresh-token.secret}")
  private String refreshSecretKey;

  @Value("${jwt.refresh-token.expiration}")
  private long refreshExpiration;

  @PostConstruct
  protected void init() {
    accessSecretKey = Base64.getEncoder().encodeToString(accessSecretKey.getBytes());
    refreshSecretKey = Base64.getEncoder().encodeToString(refreshSecretKey.getBytes());
  }

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(getSignInKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  public String generateAccessToken(UserDetails userDetails) {
    return generateAccessToken(new HashMap<>(), userDetails);
  }

  public String generateAccessToken(Map<String, Object> extraClaims, UserDetails userDetails) {
    return buildToken(extraClaims, userDetails, accessExpiration);
  }

  public String generateRefreshToken(UserDetails userDetails) {
    return buildToken(new HashMap<>(), userDetails, refreshExpiration);
  }

  private String buildToken(
      Map<String, Object> extraClaims, UserDetails userDetails, long expiration) {
    return Jwts.builder()
        .setClaims(extraClaims)
        .setSubject(userDetails.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + expiration))
        .signWith(getSignInKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token);

      return true;
    } catch (ExpiredJwtException e) {
      log.error("Expired JWT token -> Message: {}", e.getMessage());
      throw new CustomException(EXPIRED_JWT_TOKEN);
    } catch (JwtException | IllegalArgumentException e) {
      log.error("Invalid JWT token -> Message: {}", e.getMessage());
      throw new CustomException(INVALID_JWT_TOKEN);
    }
  }

  private Key getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(accessSecretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  public String getJwtFromRequest(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    throw new CustomException(INVALID_TOKEN_FORMAT);
  }
}
