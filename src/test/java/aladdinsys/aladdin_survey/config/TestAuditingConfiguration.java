package aladdinsys.aladdin_survey.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import aladdinsys.aladdin_survey.global.auditing.ApplicationAuditAware;

public class TestAuditingConfiguration {

	@Bean
	@Primary
	ApplicationAuditAware auditorProvider() {
		return new TestAuditorAware();
	}

	public static class TestAuditorAware extends ApplicationAuditAware {

		@Override
		public Optional<String> getCurrentAuditor() {
			return Optional.of("aladdin");
		}
	}
}
