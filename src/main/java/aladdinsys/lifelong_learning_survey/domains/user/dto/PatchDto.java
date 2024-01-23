package aladdinsys.lifelong_learning_survey.domains.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PatchDto(
	@NotNull(message = "사용자 ID는 필수 입니다.") @NotBlank(message = "사용자 ID는 공백이 될 수 없습니다.")
	String userId,
	@NotNull(message = "이름은 필수 입니다.") @NotBlank(message = "이름은 공백이 될 수 없습니다.")
	String name,
	@NotNull(message = "E-mail은 필수 입니다.") @NotBlank(message = "E-mail은 공백이 될 수 없습니다.")
	String email,
	@NotNull(message = "부서 코드는 필수 입니다.") @NotBlank(message = "부서 코드는 공백이 될 수 없습니다.")
	String code
) {
}
