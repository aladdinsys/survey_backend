/* (C) 2024 */
package aladdinsys.lifelong_learning_survey.domains.user.controller;

import aladdinsys.lifelong_learning_survey.domains.user.dto.UserResponseDto;
import aladdinsys.lifelong_learning_survey.domains.user.service.UserService;
import aladdinsys.lifelong_learning_survey.global.response.DataResponseBody;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService service;

  @GetMapping
  public DataResponseBody<List<UserResponseDto>> getEmployees() {
    return DataResponseBody.of(service.findAll());
  }

  @GetMapping(value = "/my-info", produces = "application/json")
  public DataResponseBody<UserResponseDto> getMyInfo(Principal principal) {
    return DataResponseBody.of(service.myInfo(principal));
  }
}
