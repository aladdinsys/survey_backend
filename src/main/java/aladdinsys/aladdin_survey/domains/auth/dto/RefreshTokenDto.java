/* (C) 2024 AladdinSystem License */
package aladdinsys.aladdin_survey.domains.auth.dto;

import lombok.Builder;

@Builder
public record RefreshTokenDto(String accessToken, String refreshToken) {}
