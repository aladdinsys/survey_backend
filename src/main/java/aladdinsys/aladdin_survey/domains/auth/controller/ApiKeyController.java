/* (C) 2024 AladdinSystem License */
package aladdinsys.aladdin_survey.domains.auth.controller;

import aladdinsys.aladdin_survey.domains.auth.dto.ApiKeyResponseDto;
import aladdinsys.aladdin_survey.domains.auth.service.ApiKeyService;
import aladdinsys.aladdin_survey.global.response.DataResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
