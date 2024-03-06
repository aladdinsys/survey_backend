package aladdinsys.aladdin_survey.domains.auth.dto;

import lombok.Builder;

@Builder
public record ApiKeyResponseDto(String key) {
}
