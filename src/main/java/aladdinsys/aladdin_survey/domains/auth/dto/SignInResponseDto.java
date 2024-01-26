/* (C) 2024 AladdinSystem License */
package aladdinsys.aladdin_survey.domains.auth.dto;

import aladdinsys.aladdin_survey.domains.user.constant.Role;
import lombok.Builder;

@Builder
public record SignInResponseDto(
    String accessToken, String refreshToken, Role role, String name, String code) {}
