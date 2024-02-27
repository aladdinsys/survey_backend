/* (C) 2024 AladdinSystem License */
package aladdinsys.aladdin_survey.domains.survey.controller;

import static aladdinsys.aladdin_survey.global.constant.SuccessCode.*;

import aladdinsys.aladdin_survey.domains.survey.dto.SurveyRequest;
import aladdinsys.aladdin_survey.domains.survey.dto.SurveyResponse;
import aladdinsys.aladdin_survey.domains.survey.service.SurveyService;
import aladdinsys.aladdin_survey.global.response.DataResponseBody;
import aladdinsys.aladdin_survey.global.response.ResponseBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/surveys")
@Tag(name = "Survey", description = "Survey API")
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

  @GetMapping(value = "/{id}")
  public DataResponseBody<SurveyResponse> getFindById(
      @PathVariable("id") Long id, Principal principal) {
    return DataResponseBody.of(service.findById(id, principal));
  }

  @PostMapping(produces = "application/json")
  public DataResponseBody<SurveyResponse> post(
      @Valid @RequestBody SurveyRequest request, Principal principal) {
    return DataResponseBody.of(service.save(request, principal));
  }

  @PatchMapping(value = "/{id}", produces = "application/json")
  public ResponseBody patch(
      @PathVariable("id") Long id, @RequestBody SurveyRequest request, Principal principal) {
    service.update(id, request, principal);
    return ResponseBody.of(SUCCESS_PATCH);
  }

  @PatchMapping(value = "/{id}/publish", produces = "application/json")
  public DataResponseBody<SurveyResponse> publish(
      @PathVariable("id") Long id, Principal principal) {
    return DataResponseBody.of(service.publish(id, principal));
  }

  @PatchMapping(value = "/publish", produces = "application/json")
  public DataResponseBody<SurveyResponse> publish(
      @RequestBody SurveyRequest request, Principal principal) {
    return DataResponseBody.of(service.publish(request, principal));
  }

  @DeleteMapping(value = "/{id}", produces = "application/json")
  public ResponseBody delete(@PathVariable("id") Long id, Principal principal) {
    service.delete(id, principal);
    return ResponseBody.of(SUCCESS_DELETE);
  }
}
