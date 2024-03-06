package aladdinsys.aladdin_survey.global.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import aladdinsys.aladdin_survey.domains.auth.service.ApiKeyService;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApiKeyAuthFilter extends OncePerRequestFilter{

	private final ApiKeyService apiKeyService;

	@Override
	protected void doFilterInternal(
		@Nonnull HttpServletRequest request,
		@Nonnull HttpServletResponse response,
		@Nonnull FilterChain filterChain)
		throws ServletException, IOException {

		String apiKey = request.getHeader("API-KEY");

		if (apiKeyService.isValidApiKey(apiKey)) {
			SimpleGrantedAuthority apiKeyAuthority = new SimpleGrantedAuthority("API-KEY");
			Authentication authentication = new ApiKeyAuthenticationToken(apiKey, Collections.singletonList(apiKeyAuthority));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		filterChain.doFilter(request, response);

	}
}
