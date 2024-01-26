package aladdinsys.aladdin_survey.domains.survey.service;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Service;

import aladdinsys.aladdin_survey.domains.survey.dto.SurveyRequest;
import aladdinsys.aladdin_survey.domains.survey.dto.SurveyResponse;
import aladdinsys.aladdin_survey.domains.survey.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SurveyService {

	private final SurveyRepository surveyRepository;

	public void save(final SurveyRequest request) {
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
		// TODO Auto-generated method stub

		return null;
	}
}
