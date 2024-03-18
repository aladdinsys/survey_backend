/* (C) 2024 AladdinSystem License */
package aladdinsys.aladdin_survey.domains.survey.dto;

import aladdinsys.aladdin_survey.domains.survey.entity.Spatial;
import lombok.Builder;

@Builder
public record SurveyResponse(
    Long id,
    String title,
    String description,
    String content,
    Spatial center,
    String publishId,
    String owner,
    String createdAt,
    String updatedAt,
    String publishedAt) {}
