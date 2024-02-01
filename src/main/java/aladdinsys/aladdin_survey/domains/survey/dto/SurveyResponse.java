/* (C) 2024 AladdinSystem License */
package aladdinsys.aladdin_survey.domains.survey.dto;

import lombok.Builder;

@Builder
public record SurveyResponse(
    Long id,
    String title,
    String description,
    String content,
	String publishId,
    String owner,
    String createdAt,
    String updatedAt,
    String publishedAt
) {}
