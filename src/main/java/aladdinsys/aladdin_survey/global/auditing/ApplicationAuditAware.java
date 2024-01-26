/* (C) 2024 AladdinSystem License */
package aladdinsys.aladdin_survey.global.auditing;

import aladdinsys.aladdin_survey.global.security.CustomUserDetails;
import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class ApplicationAuditAware implements AuditorAware<String> {

  @NonNull
  @Override
  public Optional<String> getCurrentAuditor() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null
        || !authentication.isAuthenticated()
        || authentication instanceof AnonymousAuthenticationToken) {
      return Optional.empty();
    }

    CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
    return Optional.ofNullable(userDetails.getUsername());
  }
}
