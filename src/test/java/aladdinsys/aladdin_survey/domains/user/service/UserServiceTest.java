package aladdinsys.aladdin_survey.domains.user.service;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import aladdinsys.aladdin_survey.domains.auth.dto.SignUpRequestDto;
import aladdinsys.aladdin_survey.domains.auth.service.AuthenticationService;
import aladdinsys.aladdin_survey.domains.user.constant.Role;
import aladdinsys.aladdin_survey.domains.user.dto.PatchDto;
import aladdinsys.aladdin_survey.domains.user.dto.ResponseDto;
import aladdinsys.aladdin_survey.domains.user.repository.UserRepository;
import jakarta.persistence.EntityManager;

@SpringBootTest
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceTest {

	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationService authService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	EntityManager em;

	@BeforeEach
	void setUp() {
		authService.signUp(new SignUpRequestDto("testId", "testPassword", "홍길동", "12345", "hongildong@aladdinsys.co.kr"));
		authService.signUp(new SignUpRequestDto("k2ngis", "testPassword2", "고경남", "12345", "k2ngis@aladdinsys.co.kr"));

		em.clear();
	}

	@Test
	@DisplayName("회원 정보 By ID 조회")
	@Order(1)
	void findById() {
		ResponseDto result = userService.findById(2L);

		assertThat(result.name()).isEqualTo("홍길동");
		assertThat(result.email()).isEqualTo("hongildong@aladdinsys.co.kr");
		assertThat(result.role()).isEqualTo(Role.USER);
	}

	@Test
	@DisplayName("회원 정보 전체 조회")
	@Order(2)
	void findAll() {
		List<ResponseDto> result = userService.findAll();

		assertThat(result.size()).isEqualTo(3);
		assertThat(result.get(1).name()).isEqualTo("홍길동");
		assertThat(result.get(2).name()).isEqualTo("고경남");
	}

	@Test
	@DisplayName("회원 정보 수정")
	@Order(3)
	void patch() {

		var dto = new PatchDto("홍길동",  "hong@aladdin.co.kr", "00000");
		userService.patch(6L, dto);

		em.flush();

		userRepository.findById(6L).ifPresentOrElse(
				user -> {
					assertThat(user.getName()).isEqualTo("홍길동");
					assertThat(user.getEmail()).isEqualTo("hong@aladdin.co.kr");
					assertThat(user.getCode()).isEqualTo("00000");
				},
				() -> fail("회원 정보 수정 실패")
		);
	}

	@Test
	@DisplayName("회원 비밀번호 수정")
	@Order(4)
	void changePassword() {

		userRepository.findById(8L).ifPresentOrElse(
				user -> user.changePassword("testPassword"),
				() -> fail("회원 비밀번호 수정 실패")
		);

		em.flush();

		userRepository.findById(8L).ifPresentOrElse(
			user -> assertThat(user.getPassword()).isEqualTo("testPassword"),
			() -> fail("회원 비밀번호 수정 실패")
		);
	}

	@Test
	@DisplayName("회원 삭제")
	@Order(5)
	void delete() {
		userService.delete(10L);

		em.flush();

		assertThat(userRepository.findById(10L).isEmpty()).isTrue();
	}
}