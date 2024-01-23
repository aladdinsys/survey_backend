package aladdinsys.lifelong_learning_survey.domains.user.dto;

public record ChangePasswordDto(
	String password,
	String newPassword
) {
}
