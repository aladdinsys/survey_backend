package aladdinsys.aladdin_survey.global.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aladdinsys.aladdin_survey.domains.survey.dto.SurveyResponse;
import aladdinsys.aladdin_survey.global.response.DataResponseBody;

@RestController
@RequestMapping("/open-api")
public class OpenApiController {

	@RequestMapping("/survey/{publishId}")
	public DataResponseBody<SurveyResponse> getSurvey(
		@PathVariable String publishId
	) {
		return null;
	}
}
