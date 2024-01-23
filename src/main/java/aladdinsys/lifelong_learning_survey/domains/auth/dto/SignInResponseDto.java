/* (C) 2024 */
package aladdinsys.lifelong_learning_survey.domains.auth.dto;

import aladdinsys.lifelong_learning_survey.domains.user.constant.Role;
import lombok.Builder;

@Builder
public record SignInResponseDto(
    String accessToken, String refreshToken, Role role, String name, String code) {}
