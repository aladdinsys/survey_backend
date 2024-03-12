/* (C) 2024 AladdinSystem License */
package aladdinsys.aladdin_survey.domains.auth.service;

import static aladdinsys.aladdin_survey.global.constant.ErrorCode.*;

import aladdinsys.aladdin_survey.domains.auth.dto.RefreshTokenDto;
import aladdinsys.aladdin_survey.domains.auth.dto.SignInRequestDto;
import aladdinsys.aladdin_survey.domains.auth.dto.SignInResponseDto;
import aladdinsys.aladdin_survey.domains.auth.dto.SignUpRequestDto;
import aladdinsys.aladdin_survey.domains.user.constant.Role;
import aladdinsys.aladdin_survey.domains.user.entity.User;
import aladdinsys.aladdin_survey.domains.user.repository.UserRepository;
import aladdinsys.aladdin_survey.global.exception.CustomException;
import aladdinsys.aladdin_survey.global.security.CustomUserDetails;
import aladdinsys.aladdin_survey.global.security.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  private final JwtProvider jwtProvider;

  private final AuthenticationManager authenticationManager;

  @Transactional
  public void signUpAdmin(final SignUpRequestDto signUpRequestDto) {

    var fetch = userRepository.findByUserId(signUpRequestDto.userId());
    if (fetch.isEmpty()) {
      var user =
          User.builder()
              .userId(signUpRequestDto.userId())
              .password(passwordEncoder.encode(signUpRequestDto.password()))
              .name(signUpRequestDto.name())
              .code(signUpRequestDto.code())
              .email(signUpRequestDto.email())
              .role(Role.ADMIN)
              .build();

      userRepository.save(user);
    }
  }

  @Transactional
  public void signUp(final SignUpRequestDto signUpRequestDto) {

    var userId = signUpRequestDto.userId();
    userRepository
        .findByUserId(userId)
        .ifPresent(
            user -> {
              throw new CustomException(DUPLICATE_USERID);
            });

    boolean isValid = validateEmail(signUpRequestDto.email());
    if (!isValid) {
      throw new CustomException(INVALID_EMAIL);
    }

    // TODO CODE 정규식 검사 필요?

    var user =
        User.builder()
            .userId(signUpRequestDto.userId())
            .password(passwordEncoder.encode(signUpRequestDto.password()))
            .name(signUpRequestDto.name())
            .code(signUpRequestDto.code())
            .email(signUpRequestDto.email())
            .build();

    userRepository.save(user);
  }

  private boolean validateEmail(String email) {
    var regexPattern =
        "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    return Pattern.compile(regexPattern).matcher(email).matches();
  }

  @Transactional
  public SignInResponseDto signIn(final SignInRequestDto signInRequestDto) {

    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              signInRequestDto.userId(), signInRequestDto.password()));
    } catch (AuthenticationException e) {
      log.error("Invalid Id or Password : {}", e.getMessage());
      throw new CustomException(INVALID_ID_OR_PASSWORD);
    }

    User user =
        userRepository
            .findByUserId(signInRequestDto.userId())
            .orElseThrow(() -> new CustomException(NOT_FOUND_USER));

    CustomUserDetails userDetails = new CustomUserDetails(user);

    String jwtToken = jwtProvider.generateAccessToken(userDetails);
    String refreshToken = jwtProvider.generateRefreshToken(userDetails);

    return SignInResponseDto.builder()
        .accessToken(jwtToken)
        .refreshToken(refreshToken)
        .role(user.getRole())
        .name(user.getName())
        .code(user.getCode())
        .build();
  }

  public RefreshTokenDto refreshToken(HttpServletRequest request) {

    String refreshToken = jwtProvider.getJwtFromRequest(request);

    String accessToken;
    String newRefreshToken;

    if (!jwtProvider.isRefreshTokenValid(refreshToken)) {
      log.error("Refresh Token Error :" + refreshToken);
      throw new CustomException(INVALID_JWT_TOKEN);
    }

    String userId = jwtProvider.extractUsername(refreshToken, true);

    CustomUserDetails userDetails =
        userRepository
            .findByUserId(userId)
            .map(CustomUserDetails::new)
            .orElseThrow(() -> new CustomException(NOT_FOUND_USER));

    accessToken = jwtProvider.generateAccessToken(userDetails);
    newRefreshToken = jwtProvider.generateRefreshToken(userDetails);

    return RefreshTokenDto.builder().accessToken(accessToken).refreshToken(newRefreshToken).build();
  }
}
