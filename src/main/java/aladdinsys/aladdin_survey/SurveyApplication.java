/* (C) 2024 AladdinSystem License */
package aladdinsys.aladdin_survey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class SurveyApplication {
  public static void main(String[] args) {
    SpringApplication.run(SurveyApplication.class, args);
  }
}
