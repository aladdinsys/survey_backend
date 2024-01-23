/* (C) 2024 */
package aladdinsys.lifelong_learning_survey.domains.auth.service;

import static aladdinsys.lifelong_learning_survey.global.constant.ErrorCode.*;

import aladdinsys.lifelong_learning_survey.domains.auth.dto.RefreshTokenDto;
import aladdinsys.lifelong_learning_survey.domains.auth.dto.SignInRequestDto;
import aladdinsys.lifelong_learning_survey.domains.auth.dto.SignInResponseDto;
import aladdinsys.lifelong_learning_survey.domains.auth.dto.SignUpRequestDto;
import aladdinsys.lifelong_learning_survey.domains.user.entity.User;
import aladdinsys.lifelong_learning_survey.domains.user.repository.UserRepository;
import aladdinsys.lifelong_learning_survey.global.exception.CustomException;
import aladdinsys.lifelong_learning_survey.global.security.CustomUserDetails;
import aladdinsys.lifelong_learning_survey.global.security.JwtProvider;
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
    String userId = jwtProvider.extractUsername(refreshToken);

    CustomUserDetails userDetails =
        userRepository
            .findByUserId(userId)
            .map(CustomUserDetails::new)
            .orElseThrow(() -> new CustomException(NOT_FOUND_USER));

    if (!jwtProvider.validateToken(refreshToken)) {
      throw new CustomException(INVALID_JWT_TOKEN);
    }

    String accessToken = jwtProvider.generateAccessToken(userDetails);
    String newRefreshToken = jwtProvider.generateRefreshToken(userDetails);

    return new RefreshTokenDto(accessToken, newRefreshToken);
  }
}
