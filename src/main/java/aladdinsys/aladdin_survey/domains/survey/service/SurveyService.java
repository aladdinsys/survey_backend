/* (C) 2024 AladdinSystem License */
package aladdinsys.aladdin_survey.domains.survey.service;

import static aladdinsys.aladdin_survey.global.constant.ErrorCode.*;

import aladdinsys.aladdin_survey.domains.survey.dto.SurveyRequest;
import aladdinsys.aladdin_survey.domains.survey.dto.SurveyResponse;
import aladdinsys.aladdin_survey.domains.survey.entity.Survey;
import aladdinsys.aladdin_survey.domains.survey.repository.SurveyRepository;
import aladdinsys.aladdin_survey.global.exception.CustomException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SurveyService {

  private final SurveyRepository repository;

  @Transactional
  public void save(final SurveyRequest request, Principal principal) {

    Survey survey =
        Survey.builder()
            .title(request.title())
            .description(request.description())
            .content(request.content())
            .owner(principal.getName())
            .build();

    repository.save(survey);
  }

  @Transactional
  public void update(final Long id, final SurveyRequest request, final Principal principal) {

    Survey survey =
        repository.findById(id).orElseThrow(() -> new CustomException(NOT_FOUND_SURVEY));

    if (!survey.getOwner().equals(principal.getName())) {
      throw new CustomException(NOT_AUTHORIZED);
    }

    survey.update(request.title(), request.description(), request.content());
  }

  @Transactional
  public SurveyResponse publish(final Long id, final Principal principal) {

    Survey survey =
        repository.findById(id).orElseThrow(() -> new CustomException(NOT_FOUND_SURVEY));

    if (!survey.getOwner().equals(principal.getName())) {
      throw new CustomException(NOT_AUTHORIZED);
    }

    survey.publish();

    return this.toResponseDTO(survey);
  }

  @Transactional
  public void delete(final Long id, final Principal principal) {

    Survey survey =
        repository.findById(id).orElseThrow(() -> new CustomException(NOT_FOUND_SURVEY));

    if (!survey.getOwner().equals(principal.getName())) {
      throw new CustomException(NOT_AUTHORIZED);
    }

    repository.delete(survey);
  }

  @Transactional(readOnly = true)
  public List<SurveyResponse> findAll() {
    List<Survey> surveys = repository.findAll();
    return surveys.stream().map(this::toResponseDTO).toList();
  }

  @Transactional(readOnly = true)
  public SurveyResponse findById(final Long id, final Principal principal) {
    Survey survey =
        repository.findById(id).orElseThrow(() -> new CustomException(NOT_FOUND_SURVEY));

    if (!survey.getOwner().equals(principal.getName())) {
      throw new CustomException(NOT_AUTHORIZED);
    }

    return this.toResponseDTO(survey);
  }

  @Transactional(readOnly = true)
  public List<SurveyResponse> findOwn(final Principal principal) {

    String userId = principal.getName();
    List<Survey> surveys = repository.findByOwner(userId);

    return surveys.stream().map(this::toResponseDTO).toList();
  }

  private SurveyResponse toResponseDTO(Survey survey) {
    return SurveyResponse.builder()
        .id(survey.getId())
        .title(survey.getTitle())
        .description(survey.getDescription())
        .content(survey.getContent())
        .publishId(survey.getPublishId())
        .owner(survey.getOwner())
        .createdAt(getDateTimeString(survey.getCreatedAt()))
        .updatedAt(getDateTimeString(survey.getUpdatedAt()))
        .publishedAt(getDateTimeString(survey.getPublishedAt()))
        .build();
  }

  private String getDateTimeString(LocalDateTime dateTime) {

    if (dateTime == null) {
      return "";
    }

    return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
  }
}
