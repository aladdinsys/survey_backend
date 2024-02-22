/* (C) 2024 AladdinSystem License */
package aladdinsys.aladdin_survey.domains.user.controller;

import static aladdinsys.aladdin_survey.global.constant.SuccessCode.*;

import aladdinsys.aladdin_survey.domains.user.dto.ChangePasswordDto;
import aladdinsys.aladdin_survey.domains.user.dto.PatchDto;
import aladdinsys.aladdin_survey.domains.user.dto.ResponseDto;
import aladdinsys.aladdin_survey.domains.user.service.UserService;
import aladdinsys.aladdin_survey.global.response.DataResponseBody;
import aladdinsys.aladdin_survey.global.response.ResponseBody;
import java.util.List;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "User", description = "User API")
public class UserController {

  private final UserService service;

  @GetMapping
  public DataResponseBody<List<ResponseDto>> getUsers() {
    return DataResponseBody.of(service.findAll());
  }

  @GetMapping(value = "/{id}", produces = "application/json")
  public DataResponseBody<ResponseDto> getUser(@PathVariable("id") Long id) {
    return DataResponseBody.of(service.findById(id));
  }

  @PatchMapping(value = "/{id}", produces = "application/json")
  public ResponseBody patchUserInfo(@PathVariable("id") Long id, @RequestBody PatchDto dto) {
    service.patch(id, dto);
    return ResponseBody.of(SUCCESS_PATCH);
  }

  @PatchMapping(value = "/{id}/change-password", produces = "application/json")
  public ResponseBody changePassword(
      @PathVariable("id") Long id, @RequestBody ChangePasswordDto dto) {
    service.changePassword(id, dto);
    return ResponseBody.of(SUCCESS_PATCH);
  }

  @DeleteMapping(value = "/{id}", produces = "application/json")
  public ResponseBody deleteUser(@PathVariable("id") Long id) {
    service.delete(id);
    return ResponseBody.of(SUCCESS_DELETE);
  }
}
