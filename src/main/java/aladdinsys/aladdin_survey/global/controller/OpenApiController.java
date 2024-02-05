package aladdinsys.aladdin_survey.global.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aladdinsys.aladdin_survey.domains.survey.dto.SurveyResponse;
import aladdinsys.aladdin_survey.domains.survey.service.SurveyService;
import aladdinsys.aladdin_survey.global.response.DataResponseBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/open-api")
@RequiredArgsConstructor
public class OpenApiController {

	private final SurveyService surveyService;

	@GetMapping("/survey/{publishId}")
	public DataResponseBody<SurveyResponse> getSurvey(
		@PathVariable("publishId") String publishId
	) {
		return DataResponseBody.of(surveyService.findByPublishId(publishId));
	}
}
