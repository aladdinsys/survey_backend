/* (C) 2024 AladdinSystem License */
package aladdinsys.aladdin_survey.domains.auth.controller;

import static aladdinsys.aladdin_survey.global.constant.SuccessCode.*;

import aladdinsys.aladdin_survey.domains.auth.dto.SignInRequestDto;
import aladdinsys.aladdin_survey.domains.auth.dto.SignInResponseDto;
import aladdinsys.aladdin_survey.domains.auth.dto.SignUpRequestDto;
import aladdinsys.aladdin_survey.domains.auth.service.AuthenticationService;
import aladdinsys.aladdin_survey.global.response.DataResponseBody;
import aladdinsys.aladdin_survey.global.response.ResponseBody;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  @PostMapping(value = "/sign-up")
  public ResponseBody signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
    authenticationService.signUp(signUpRequestDto);
    return ResponseBody.of(SUCCESS_CREATE);
  }

  @PostMapping(value = "/sign-in", produces = "application/json")
  public DataResponseBody<SignInResponseDto> signIn(
      @RequestBody SignInRequestDto signInRequestDto) {
    return DataResponseBody.of(authenticationService.signIn(signInRequestDto));
  }

  @PostMapping(value = "/refresh-token")
  public DataResponseBody<SignInResponseDto> refreshToken(HttpServletRequest request) {
    return DataResponseBody.of(authenticationService.refreshToken(request));
  }
}
