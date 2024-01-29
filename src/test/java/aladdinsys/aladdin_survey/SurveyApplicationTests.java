package aladdinsys.aladdin_survey;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import aladdinsys.aladdin_survey.config.TestAuditingConfiguration;

@SpringBootTest
@EnableJpaAuditing
@Import(TestAuditingConfiguration.class)
class SurveyApplicationTests {

	@Test
	void contextLoads() {
	}

}
