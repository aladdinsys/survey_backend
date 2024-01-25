/* (C) 2024 AladdinSystem License */
package aladdinsys.lifelong_learning_survey;

import aladdinsys.lifelong_learning_survey.domains.auth.dto.SignUpRequestDto;
import aladdinsys.lifelong_learning_survey.domains.auth.service.AuthenticationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
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
