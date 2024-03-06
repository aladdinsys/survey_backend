package aladdinsys.aladdin_survey.domains.auth.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import aladdinsys.aladdin_survey.domains.auth.dto.ApiKeyResponseDto;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
class ApiKeyServiceTest {

	@Autowired
	private ApiKeyService service;

	@Test
	@DisplayName("키 발행 테스트 / 키 검증 테스트 - 유효한 키")
	void generateApiKey() throws Exception {

		ApiKeyResponseDto dto = service.generateApiKey();
		String key = dto.key();

		assertThat(key.length()).isEqualTo(36);
		assertThat(service.isValidApiKey(key)).isTrue();
	}

	@Test
	@DisplayName("키 검증 테스트 - 유효하지 않은 키")
	void validApiKeyFailure() throws Exception {

		ApiKeyResponseDto dto = service.generateApiKey();

		String uuid = UUID.randomUUID().toString();

		assertThat(service.isValidApiKey(uuid)).isFalse();
		assertThat(dto.key()).isNotEqualTo(uuid);
	}
}