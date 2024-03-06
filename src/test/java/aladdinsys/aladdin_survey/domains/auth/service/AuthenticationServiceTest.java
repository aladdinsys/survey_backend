package aladdinsys.aladdin_survey.domains.auth.service;

import static aladdinsys.aladdin_survey.global.constant.ErrorCode.*;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jayway.jsonpath.JsonPath;

import aladdinsys.aladdin_survey.domains.auth.dto.SignInRequestDto;
import aladdinsys.aladdin_survey.domains.auth.dto.SignInResponseDto;
import aladdinsys.aladdin_survey.domains.auth.dto.SignUpRequestDto;
import aladdinsys.aladdin_survey.domains.user.constant.Role;
import aladdinsys.aladdin_survey.domains.user.entity.User;
import aladdinsys.aladdin_survey.domains.user.repository.UserRepository;
import aladdinsys.aladdin_survey.global.constant.SuccessCode;
import aladdinsys.aladdin_survey.global.exception.CustomException;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
class AuthenticationServiceTest {

	@Autowired
	private AuthenticationService service;

	@Autowired
	EntityManager em;

	@Autowired
	private UserRepository userRepository;

	@Test
	@DisplayName("관리자 등록 - 성공")
	void signUpAdmin() {

		SignUpRequestDto dto = SignUpRequestDto.builder()
			.userId("admin")
			.password("user00!")
			.name("관리자")
			.code("0000")
			.email("nadk@aladdinsys.co.kr")
			.build();

		service.signUpAdmin(dto);

		em.flush();
		em.clear();

		User admin = userRepository.findByUserId("admin").orElseThrow();

		assertThat(admin.getUserId()).isEqualTo("admin");
		assertThat(admin.getName()).isEqualTo("관리자");
		assertThat(admin.getRole()).isEqualTo(Role.ADMIN);
	}

	@Test
	@DisplayName("사용자 등록 - 성공")
	void signUp() {

		SignUpRequestDto dto = SignUpRequestDto.builder()
			.userId("k2ngis")
			.password("user00!")
			.name("고경남")
			.code("0000")
			.email("k2ngis@aladdinsys.co.kr")
			.build();

		service.signUp(dto);

		em.flush();
		em.clear();

		User user = userRepository.findByUserId("k2ngis").orElseThrow();

		assertThat(user.getUserId()).isEqualTo("k2ngis");
		assertThat(user.getName()).isEqualTo("고경남");
		assertThat(user.getRole()).isEqualTo(Role.USER);
	}

	@Test
	@DisplayName("로그인 - 성공")
	void signIn() {

		SignUpRequestDto dto = SignUpRequestDto.builder()
			.userId("k2ngis")
			.password("user00!")
			.name("고경남")
			.code("0000")
			.email("k2ngis@aladdinsys.co.kr")
			.build();

		service.signUp(dto);

		em.flush();
		em.clear();

		SignInRequestDto request = new SignInRequestDto("k2ngis","user00!");
		SignInResponseDto response = service.signIn(request);

		assertThat(response.accessToken()).isNotNull();
		assertThat(response.refreshToken()).isNotNull();
		assertThat(response.name()).isEqualTo("고경남");
		assertThat(response.role()).isEqualTo(Role.USER);
	}

	@Test
	@DisplayName("로그인 - 실패(비밀 번호 불일치)")
	void signInFailure() {

		SignUpRequestDto dto = SignUpRequestDto.builder()
			.userId("k2ngis")
			.password("user00!")
			.name("고경남")
			.code("0000")
			.email("k2ngis@aladdinsys.co.kr")
			.build();

		service.signUp(dto);

		em.flush();
		em.clear();

		CustomException exception = assertThrows(CustomException.class, () -> {
			SignInRequestDto request = new SignInRequestDto("k2ngis","wrongPassword");
			SignInResponseDto response = service.signIn(request);
		});


		assertThat(exception.getErrorCode()).isEqualTo(INVALID_ID_OR_PASSWORD);
	}
}