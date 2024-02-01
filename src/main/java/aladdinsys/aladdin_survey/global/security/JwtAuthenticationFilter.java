/* (C) 2024 AladdinSystem License */
package aladdinsys.aladdin_survey.global.security;

import static aladdinsys.aladdin_survey.global.constant.ErrorCode.*;

import aladdinsys.aladdin_survey.global.exception.CustomException;
import aladdinsys.aladdin_survey.global.response.ErrorResponseBody;
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
import org.springframework.util.StringUtils;
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
          .filter(jwtProvider::isAccessTokenValid)
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
    return !path.matches("^/favicon.*")
        && !path.matches("^/libs.*")
        && !path.matches("^/js.*")
        && !path.matches("^/style.*")
        && !path.matches("^/view.*")
        && !path.matches("^/auth/.*")
        && !path.matches("^/error.*");

  }
}
