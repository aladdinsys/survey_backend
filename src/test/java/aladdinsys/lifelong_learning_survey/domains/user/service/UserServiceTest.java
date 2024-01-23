package aladdinsys.lifelong_learning_survey.domains.user.service;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import aladdinsys.lifelong_learning_survey.domains.auth.dto.SignUpRequestDto;
import aladdinsys.lifelong_learning_survey.domains.auth.service.AuthenticationService;
import aladdinsys.lifelong_learning_survey.domains.user.constant.Role;
import aladdinsys.lifelong_learning_survey.domains.user.dto.ResponseDto;
import jakarta.persistence.EntityManager;

@SpringBootTest
@Transactional
class UserServiceTest {

	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationService authService;
	@Autowired
	EntityManager em;

	@BeforeEach
	void setUp() {
		authService.signUp(new SignUpRequestDto("testId", "testPassword", "홍길동", "12345", "hongildong@aladdinsys.co.kr"));
		authService.signUp(new SignUpRequestDto("k2ngis", "testPassword2", "고경남", "12345", "k2ngis@aladdinsys.co.kr"));

		em.clear();
	}
	@Test
	@DisplayName("회원 정보 전체 조회")
	void findAll() {
		List<ResponseDto> result = userService.findAll();

		assertThat(result.size()).isEqualTo(2);
		assertThat(result.get(0).name()).isEqualTo("홍길동");
		assertThat(result.get(1).name()).isEqualTo("고경남");
	}

	@Test
	@DisplayName("회원 정보 By ID 조회")
	void findById() {
		ResponseDto result = userService.findById(1L);

		assertThat(result.name()).isEqualTo("홍길동");
		assertThat(result.email()).isEqualTo("hongildong@aladdinsys.co.kr");
		assertThat(result.role()).isEqualTo(Role.USER);
	}

	@Test
	void patch() {
	}

	@Test
	void changePassword() {
	}

	@Test
	void delete() {
	}

	@Test
	void myInfo() {
	}
}