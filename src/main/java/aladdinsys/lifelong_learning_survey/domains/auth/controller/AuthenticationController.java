package aladdinsys.lifelong_learning_survey.domains.auth.controller;

import static aladdinsys.lifelong_learning_survey.global.constant.SuccessCode.*;
import static org.springframework.http.HttpStatus.*;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aladdinsys.lifelong_learning_survey.domains.auth.dto.SignInRequestDto;
import aladdinsys.lifelong_learning_survey.domains.auth.dto.SignInResponseDto;
import aladdinsys.lifelong_learning_survey.domains.auth.dto.SignUpRequestDto;
import aladdinsys.lifelong_learning_survey.domains.auth.service.AuthenticationService;
import aladdinsys.lifelong_learning_survey.global.response.DataResponseBody;
import aladdinsys.lifelong_learning_survey.global.response.ResponseBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

	private final AuthenticationService authenticationService;

	@PostMapping(value = "/sign-up")
	public ResponseBody signUp(
		@RequestBody SignUpRequestDto signUpRequestDto
	) {
		authenticationService.signUp(signUpRequestDto);
		return ResponseBody.of(SUCCESS_CREATE);
	}

	@PostMapping(value = "/sign-in", produces = "application/json", consumes = "application/json")
	public DataResponseBody<SignInResponseDto> signIn(
		@RequestBody SignInRequestDto signInRequestDto
	) {
		return DataResponseBody.of(authenticationService.signIn(signInRequestDto));
	}

}
