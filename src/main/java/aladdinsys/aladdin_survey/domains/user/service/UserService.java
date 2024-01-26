/* (C) 2024 AladdinSystem License */
package aladdinsys.aladdin_survey.domains.user.service;

import static aladdinsys.aladdin_survey.global.constant.ErrorCode.*;

import aladdinsys.aladdin_survey.domains.user.dto.ChangePasswordDto;
import aladdinsys.aladdin_survey.domains.user.dto.PatchDto;
import aladdinsys.aladdin_survey.domains.user.dto.ResponseDto;
import aladdinsys.aladdin_survey.domains.user.entity.User;
import aladdinsys.aladdin_survey.domains.user.repository.UserRepository;
import aladdinsys.aladdin_survey.global.exception.CustomException;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository repository;

  private final PasswordEncoder passwordEncoder;

  @Transactional(readOnly = true)
  public List<ResponseDto> findAll() {
    List<User> users = repository.findAll();

    return users.stream()
        .map(
            user ->
                new ResponseDto(
                    user.getUserId(),
                    user.getName(),
                    user.getEmail(),
                    user.getCode(),
                    user.getRole()))
        .toList();
  }

  @Transactional(readOnly = true)
  public ResponseDto findById(Long id) {
    User user = repository.findById(id).orElseThrow(() -> new CustomException(NOT_FOUND_USER));

    return ResponseDto.builder()
        .userId(user.getUserId())
        .name(user.getName())
        .email(user.getEmail())
        .code(user.getCode())
        .role(user.getRole())
        .build();
  }

  @Transactional
  public void patch(Long id, PatchDto dto) {
    User user = repository.findById(id).orElseThrow(() -> new CustomException(NOT_FOUND_USER));
    user.updateInfo(dto.name(), dto.code(), dto.email());
  }

  @Transactional
  public void changePassword(Long id, ChangePasswordDto dto) {
    User user = repository.findById(id).orElseThrow(() -> new CustomException(NOT_FOUND_USER));
    user.changePassword(passwordEncoder.encode(dto.newPassword()));
  }

  @Transactional
  public void delete(Long id) {
    repository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public ResponseDto myInfo(Principal principal) {
    var userId = principal.getName();
    User user =
        repository.findByUserId(userId).orElseThrow(() -> new CustomException(NOT_FOUND_USER));

    return new ResponseDto(
        user.getUserId(), user.getName(), user.getEmail(), user.getCode(), user.getRole());
  }
}
