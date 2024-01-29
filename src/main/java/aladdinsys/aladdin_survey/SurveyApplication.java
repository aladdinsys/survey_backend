/* (C) 2024 AladdinSystem License */
package aladdinsys.aladdin_survey;

import aladdinsys.aladdin_survey.domains.auth.dto.SignUpRequestDto;
import aladdinsys.aladdin_survey.domains.auth.service.AuthenticationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class SurveyApplication {
  public static void main(String[] args) {
    SpringApplication.run(SurveyApplication.class, args);
  }

  @Bean
  public CommandLineRunner runner(AuthenticationService service) {

    return args -> {
      SignUpRequestDto dto =
          SignUpRequestDto.builder()
              .userId("admin")
              .password("user00!")
              .name("관리자")
              .code("0000")
              .email("nadk@aladdinsys.co.kr")
              .build();

      service.signUpAdmin(dto);
    };
  }
}
