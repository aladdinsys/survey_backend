package aladdinsys.aladdin_survey.domains.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aladdinsys.aladdin_survey.domains.auth.dto.ApiKeyResponseDto;
import aladdinsys.aladdin_survey.domains.auth.dto.SignInResponseDto;
import aladdinsys.aladdin_survey.domains.auth.entity.ApiKey;
import aladdinsys.aladdin_survey.domains.auth.service.ApiKeyService;
import aladdinsys.aladdin_survey.global.response.DataResponseBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth/api-keys")
@RequiredArgsConstructor
public class ApiKeyController {

	private final ApiKeyService apiKeyService;

	@PostMapping(value = "/generate")
	public DataResponseBody<ApiKeyResponseDto> generateApiKey() {
		return DataResponseBody.of(apiKeyService.generateApiKey());
	}


}
