/* (C) 2024 AladdinSystem License */
package aladdinsys.aladdin_survey.global.security;

import aladdinsys.aladdin_survey.domains.auth.service.ApiKeyService;
import aladdinsys.aladdin_survey.global.constant.ApiKeyPath;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApiKeyAuthFilter extends OncePerRequestFilter {

  private final ApiKeyService apiKeyService;

  @Override
  protected void doFilterInternal(
      @Nonnull HttpServletRequest request,
      @Nonnull HttpServletResponse response,
      @Nonnull FilterChain filterChain)
      throws ServletException, IOException {

    String apiKey = request.getHeader("API-KEY");

    if (authenticationRequired(request)) {
      if (apiKeyService.isValidApiKey(apiKey)) {
        SimpleGrantedAuthority apiKeyAuthority = new SimpleGrantedAuthority("API-KEY");
        Authentication authentication =
            new ApiKeyAuthenticationToken(apiKey, Collections.singletonList(apiKeyAuthority));
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    }

    filterChain.doFilter(request, response);
  }

  private boolean authenticationRequired(HttpServletRequest request) {
    var path = request.getRequestURI();
    ApiKeyPath[] values = ApiKeyPath.values();
    for (ApiKeyPath value : values) {
      if (path.matches("^/" + value.getPath() + ".*")) {
        return true;
      }
    }

    return false;
  }
}
