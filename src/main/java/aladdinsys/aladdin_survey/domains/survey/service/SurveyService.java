package aladdinsys.aladdin_survey.domains.survey.service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import aladdinsys.aladdin_survey.domains.survey.dto.SurveyRequest;
import aladdinsys.aladdin_survey.domains.survey.dto.SurveyResponse;
import aladdinsys.aladdin_survey.domains.survey.entity.Survey;
import aladdinsys.aladdin_survey.domains.survey.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SurveyService {

	private final SurveyRepository repository;


	public void save(final SurveyRequest request, Principal principal) {

		var survey = Survey.builder()
			.title(request.title())
			.description(request.description())
			.content(request.content())
			.owner(principal.getName())
			.build();

		repository.save(survey);
		// TODO Auto-generated method stub

	}

	public void update(final Long id, final SurveyRequest request) {
		// TODO Auto-generated method stub

	}

	public void publish() {
		// TODO Auto-generated method stub

	}

	public void delete(final Long id) {
		// TODO Auto-generated method stub

	}

	public List<SurveyResponse> findAll() {
		// TODO Auto-generated method stub

		return null;
	}

	public SurveyResponse findById(final Long id) {
		// TODO Auto-generated method stub

		return SurveyResponse.builder().build();
	}

	public List<SurveyResponse> findOwn(Principal principal) {

		String userId = principal.getName();

		List<Survey> surveys = repository.findByOwner(userId);

		return surveys.stream().map(
			survey -> SurveyResponse.builder()
				.id(survey.getId())
				.title(survey.getTitle())
				.description(survey.getDescription())
				.content(survey.getContent())
				.owner(survey.getOwner())
				.createdAt(getDateTimeString(survey.getCreatedAt()))
				.updatedAt(getDateTimeString(survey.getUpdatedAt()))
				.publishedAt(getDateTimeString(survey.getPublishedAt()))
				.build()
		).toList();
	}

	private String getDateTimeString(LocalDateTime dateTime) {

		if(dateTime == null) {
			return "";
		}

		return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
}
