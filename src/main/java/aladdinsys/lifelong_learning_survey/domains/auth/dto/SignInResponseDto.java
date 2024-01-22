package aladdinsys.lifelong_learning_survey.domains.auth.dto;

import aladdinsys.lifelong_learning_survey.domains.user.constant.Role;

public record SignInResponseDto(
	String accessToken,
	Role role,
	String name,
	String code
) {

}
