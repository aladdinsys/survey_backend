package aladdinsys.lifelong_learning_survey.domains.auth.service;

import java.util.regex.Pattern;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aladdinsys.lifelong_learning_survey.domains.auth.dto.SignInRequestDto;
import aladdinsys.lifelong_learning_survey.domains.auth.dto.SignInResponseDto;
import aladdinsys.lifelong_learning_survey.domains.auth.dto.SignUpRequestDto;
import aladdinsys.lifelong_learning_survey.domains.user.entity.User;
import aladdinsys.lifelong_learning_survey.domains.user.repository.UserRepository;
import aladdinsys.lifelong_learning_survey.global.constant.ErrorCode;
import aladdinsys.lifelong_learning_survey.global.exception.CustomException;
import aladdinsys.lifelong_learning_survey.global.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
		userRepository.findByUserId(userId).ifPresent(user -> {
			throw new CustomException(ErrorCode.DUPLICATE_USERID);
		});

		boolean isValid = validateEmail(signUpRequestDto.email());
		if (!isValid) {
			throw new CustomException(ErrorCode.INVALID_EMAIL);
		}
		
		// TODO CODE 정규식 검사 필요?

		var user = User.builder()
			.userId(signUpRequestDto.userId())
			.password(passwordEncoder.encode(signUpRequestDto.password()))
			.name(signUpRequestDto.name())
			.code(signUpRequestDto.code())
			.email(signUpRequestDto.email())
			.build();

		userRepository.save(user);
	}

	private boolean validateEmail(String email) {
		var regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
			+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

		return Pattern.compile(regexPattern)
			.matcher(email)
			.matches();
	}

	@Transactional
	public SignInResponseDto signIn(final SignInRequestDto signInRequestDto) {

		try {
			authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
					signInRequestDto.userId(),
					signInRequestDto.password()
				)
			);
		} catch (AuthenticationException e) {
			log.error("Invalid Id or Password : {}", e.getMessage());
			throw new CustomException(ErrorCode.INVALID_ID_OR_PASSWORD);
		}
		var user = userRepository.findByUserId(signInRequestDto.userId())
			.orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

		var jwtToken = jwtProvider.generateToken(user);
		var refreshToken = jwtProvider.generateRefreshToken(user);

		return new SignInResponseDto(jwtToken, user.getRole(), user.getName(), user.getCode());
	}
}
