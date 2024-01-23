package aladdinsys.lifelong_learning_survey.domains.auth.dto;

import lombok.Builder;

@Builder
public record RefreshTokenDto(
	String accessToken,
	String refreshToken
) {
}
