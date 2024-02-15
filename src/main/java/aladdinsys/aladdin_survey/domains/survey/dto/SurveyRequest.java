/* (C) 2024 AladdinSystem License */
package aladdinsys.aladdin_survey.domains.survey.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SurveyRequest(
    @NotNull(message = "설문 제목은 Null 이 될 수 없습니다.")
        @NotBlank(message = "설문 제목은 공백이 될 수 없습니다.")
        @Size(max = 20, message = "설문 제목은 20자를 넘을 수 없습니다.")
        String title,
    @NotNull(message = "설문 설명은 Null 이 될 수 없습니다.")
        @NotBlank(message = "설문 설명은 공백이 될 수 없습니다.")
        @Size(max = 100, message = "설문 설명은 100자를 넘을 수 없습니다.")
        String description,
    @NotNull(message = "설문 내용은 Null 이 될 수 없습니다.") @NotBlank(message = "설문 내용은 공백이 될 수 없습니다.")
        String content) {}
