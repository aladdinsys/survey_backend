package aladdinsys.aladdin_survey.domains.auth.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import aladdinsys.aladdin_survey.domains.auth.dto.ApiKeyResponseDto;
import aladdinsys.aladdin_survey.domains.auth.entity.ApiKey;
import aladdinsys.aladdin_survey.domains.auth.repository.ApiKeyRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApiKeyService {

	private final ApiKeyRepository apiKeyRepository;

	public ApiKeyResponseDto generateApiKey() {
		String uuid = UUID.randomUUID().toString();
		ApiKey apiKey = new ApiKey(uuid);

		apiKeyRepository.save(apiKey);

		return ApiKeyResponseDto.builder().key(uuid).build();
	}

	public boolean isValidApiKey(String apiKey) {
		return apiKeyRepository.findByKey(apiKey) != null;
	}
}
