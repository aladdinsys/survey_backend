/* (C) 2024 AladdinSystem License */
package aladdinsys.lifelong_learning_survey.global.security;

import aladdinsys.lifelong_learning_survey.global.exception.CustomException;
import aladdinsys.lifelong_learning_survey.global.response.ErrorResponseBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtProvider jwtProvider;
  private final UserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(
      @Nonnull HttpServletRequest request,
      @Nonnull HttpServletResponse response,
      @Nonnull FilterChain filterChain)
      throws ServletException, IOException {

    try {

      Optional.of(request)
          .filter(this::extracted)
          .map(jwtProvider::getJwtFromRequest)
          .filter(jwtProvider::validateToken)
          .map(jwtProvider::extractUsername)
          .map(userDetailsService::loadUserByUsername)
          .ifPresent(userDetails -> setAuthenticationContext(request, userDetails));

    } catch (CustomException e) {

      ObjectMapper objectMapper = new ObjectMapper();
      response.setStatus(e.getErrorCode().getHttpStatus().value());
      response.setContentType(MediaType.APPLICATION_JSON_VALUE);
      response.setCharacterEncoding("UTF-8");

      var errorResponse = ErrorResponseBody.of(e.getErrorCode(), e.getErrorCode().getMessage());

      try {
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
      } catch (IOException ioException) {
        log.error(ioException.getMessage());
      }
      return;
    }

    filterChain.doFilter(request, response);
  }

  private void setAuthenticationContext(HttpServletRequest request, UserDetails userDetails) {
    final UsernamePasswordAuthenticationToken authToken =
        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

    SecurityContextHolder.getContext().setAuthentication(authToken);
  }

  private boolean extracted(HttpServletRequest request) {
    var path = request.getRequestURI();
    return !path.equals("/auth/sign-up") && !path.equals("/auth/sign-in");
  }
}
