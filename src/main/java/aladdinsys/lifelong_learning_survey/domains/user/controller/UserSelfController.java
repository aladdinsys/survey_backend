package aladdinsys.lifelong_learning_survey.domains.user.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import aladdinsys.lifelong_learning_survey.domains.user.dto.ResponseDto;
import aladdinsys.lifelong_learning_survey.domains.user.service.UserService;
import aladdinsys.lifelong_learning_survey.global.response.DataResponseBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserSelfController {

	private final UserService service;

	@GetMapping(value = "/my-info", produces = "application/json")
	public DataResponseBody<ResponseDto> getMyInfo(Principal principal) {
		return DataResponseBody.of(service.myInfo(principal));
	}
}
