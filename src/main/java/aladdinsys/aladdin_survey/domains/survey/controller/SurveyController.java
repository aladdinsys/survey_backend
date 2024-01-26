package aladdinsys.aladdin_survey.domains.survey.controller;

import static aladdinsys.aladdin_survey.global.constant.SuccessCode.*;

import java.security.Principal;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aladdinsys.aladdin_survey.domains.survey.dto.SurveyRequest;
import aladdinsys.aladdin_survey.domains.survey.dto.SurveyResponse;
import aladdinsys.aladdin_survey.domains.survey.service.SurveyService;
import aladdinsys.aladdin_survey.global.response.DataResponseBody;
import aladdinsys.aladdin_survey.global.response.ResponseBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/surveys")
public class SurveyController {

	private final SurveyService service;

	@GetMapping(value = "/find-own", produces = "application/json")
	public DataResponseBody<List<SurveyResponse>> getFindOwn(Principal principal) {
		return DataResponseBody.of(service.findOwn(principal));
	}

	@GetMapping(produces = "application/json")
	public DataResponseBody<List<SurveyResponse>> getFindAll() {
		return DataResponseBody.of(service.findAll());
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public DataResponseBody<SurveyResponse> getFindById(
		@PathVariable Long id
	) {
		return DataResponseBody.of(service.findById(id));
	}

	@PostMapping(produces = "application/json")
	public ResponseBody post(
		@RequestBody SurveyRequest request
	) {
		service.save(request);
		return ResponseBody.of(SUCCESS_CREATE);
	}
	@PatchMapping(value="/{id}", produces = "application/json")
	public ResponseBody patch(
		@PathVariable Long id,
		@RequestBody SurveyRequest request
	) {
		service.update(id, request);
		return ResponseBody.of(SUCCESS_PATCH);
	}

	@DeleteMapping(value="/{id}", produces = "application/json")
	public ResponseBody delete(
		@PathVariable Long id
	) {
		service.delete(id);
		return ResponseBody.of(SUCCESS_DELETE);
	}

}
