package aladdinsys.aladdin_survey.domains.survey.service;

import static aladdinsys.aladdin_survey.global.constant.ErrorCode.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import aladdinsys.aladdin_survey.domains.auth.dto.SignUpRequestDto;
import aladdinsys.aladdin_survey.domains.auth.service.AuthenticationService;
import aladdinsys.aladdin_survey.domains.survey.dto.SurveyRequest;
import aladdinsys.aladdin_survey.domains.survey.dto.SurveyResponse;
import aladdinsys.aladdin_survey.domains.survey.entity.Survey;
import aladdinsys.aladdin_survey.domains.survey.repository.SurveyRepository;
import aladdinsys.aladdin_survey.global.exception.CustomException;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
class SurveyServiceTest {

	@Autowired
	private AuthenticationService authService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	EntityManager em;

	@Autowired
	private SurveyService surveyService;

	@Autowired
	private SurveyRepository repository;

	@BeforeEach
	void setUp() {
		authService.signUp(new SignUpRequestDto("testId", "testPassword", "홍길동", "12345", "hongildong@aladdinsys.co.kr"));
		authService.signUp(new SignUpRequestDto("k2ngis", "testPassword2", "고경남", "12345", "k2ngis@aladdinsys.co.kr"));

		em.clear();
	}

	private void saveSurvey() {

		var auth = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken("testId", "testPassword"));
		SecurityContextHolder.getContext().setAuthentication(auth);

		var survey = Survey
			.builder()
			.title("서베이 제목1")
			.description("서베이 설명1")
			.content("{\"sections\":[{\"section_name\":\"General Knowledge\",\"next_section\":\"Science\",\"questions\":[{\"question_text\":\"What is the capital of France?\",\"answers\":[\"Paris\",\"London\",\"Berlin\"]},{\"question_text\":\"Who wrote Romeo and Juliet?\",\"answers\":[\"William Shakespeare\",\"Jane Austen\",\"Charles Dickens\"]}]},{\"section_name\":\"Science\",\"questions\":[{\"question_text\":\"What is the largest mammal on Earth?\",\"answers\":[\"Blue Whale\",\"Elephant\",\"Giraffe\"]}]}]}")
			.build();

		var survey2 = Survey
			.builder()
			.title("서베이 제목2")
			.description("서베이 설명2")
			.content("{\"sections\":[{\"section_name\":\"섹션1\",\"next_section\":\"Science\",\"questions\":[{\"question_text\":\"What is the capital of France?\",\"answers\":[\"Paris\",\"London\",\"Berlin\"]},{\"question_text\":\"Who wrote Romeo and Juliet?\",\"answers\":[\"William Shakespeare\",\"Jane Austen\",\"Charles Dickens\"]}]},{\"section_name\":\"Science\",\"questions\":[{\"question_text\":\"What is the largest mammal on Earth?\",\"answers\":[\"Blue Whale\",\"Elephant\",\"Giraffe\"]}]}]}")
			.build();

		repository.save(survey);
		repository.save(survey2);

		var auth2 = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken("k2ngis", "testPassword2"));
		SecurityContextHolder.getContext().setAuthentication(auth2);

		var survey3 = Survey
			.builder()
			.title("서베이 제목3")
			.description("서베이 설명3")
			.content("{\"sections\":[]}")
			.build();

		repository.save(survey3);
		repository.flush();

		em.clear();
	}


	@Test
	@DisplayName("설문 정보 By ID 조회")
	void findById() {

		saveSurvey();

		var id = repository.findByOwner("testId").getFirst().getId();

		var auth = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken("testId", "testPassword"));

		SurveyResponse response = surveyService.findById(id, auth);

		assertThat(response.title()).isEqualTo("서베이 제목1");
	}

	@Test
	@DisplayName("설문 정보 By ID 조회 실패 - 권한 없음")
	void findByIdFailure() {

		saveSurvey();

		var auth = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken("k2ngis", "testPassword2"));
		SecurityContextHolder.getContext().setAuthentication(auth);

		var id = repository.findByOwner("testId").getFirst().getId();

		final Authentication getAuth = SecurityContextHolder.getContext().getAuthentication();
		CustomException exception = assertThrows(CustomException.class, () -> {
			surveyService.findById(id, getAuth);
		});

		assertThat(exception.getErrorCode()).isEqualTo(NOT_AUTHORIZED);
	}

	@Test
	@DisplayName("내 설문 정보 조회")
	void findOwn() {

		saveSurvey();

		Authentication auth = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken("testId", "testPassword"));
		SecurityContextHolder.getContext().setAuthentication(auth);

		auth = SecurityContextHolder.getContext().getAuthentication();
		List<SurveyResponse> result = surveyService.findOwn(auth);

		assertThat(result.size()).isEqualTo(2);
		assertThat(result.get(0).title()).isEqualTo("서베이 제목1");
		assertThat(result.get(1).title()).isEqualTo("서베이 제목2");
	}


	@Test
	@DisplayName("전체 설문 정보 조회")
	void findAll() {

		saveSurvey();

		List<SurveyResponse> result = surveyService.findAll();

		assertThat(result.size()).isEqualTo(3);
		assertThat(result.get(0).title()).isEqualTo("서베이 제목1");
		assertThat(result.get(1).title()).isEqualTo("서베이 제목2");
		assertThat(result.get(2).title()).isEqualTo("서베이 제목3");
	}

	@Test
	@DisplayName("설문 정보 저장")
	void save() {

		var auth = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken("k2ngis", "testPassword2"));
		SecurityContextHolder.getContext().setAuthentication(auth);

		SurveyRequest request = new SurveyRequest(
			"서베이 생성 테스트 제목",
			"서베이 생성 테스트 설명",
			"{\"sections\":[]}"
		);

		surveyService.save(request, auth);

		var id = repository.findByOwner("k2ngis").getFirst().getId();
		Survey survey = repository.findById(id).orElseThrow();

		assertThat(survey.getTitle()).isEqualTo("서베이 생성 테스트 제목");
		assertThat(survey.getDescription()).isEqualTo("서베이 생성 테스트 설명");
		assertThat(survey.getContent()).isEqualTo("{\"sections\":[]}");
	}

	@Test
	@DisplayName("설문 정보 수정")
	void update() {

		saveSurvey();

		var auth = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken("k2ngis", "testPassword2"));
		SecurityContextHolder.getContext().setAuthentication(auth);

		SurveyRequest request = new SurveyRequest(
			"서베이 수정 테스트 제목",
			"서베이 수정 테스트 설명",
			"{\"sections\":[]}"
		);

		var id = repository.findByOwner("k2ngis").getFirst().getId();
		surveyService.update(id, request, auth);

		SurveyResponse response = surveyService.findById(id, auth);

		assertThat(response.title()).isEqualTo("서베이 수정 테스트 제목");
		assertThat(response.description()).isEqualTo("서베이 수정 테스트 설명");
		assertThat(response.content()).isEqualTo("{\"sections\":[]}");
	}

	@Test
	@DisplayName("설문 정보 수정 실패 - 권한 없음")
	void updateFailure() {

		saveSurvey();

		var auth = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken("k2ngis", "testPassword2"));
		SecurityContextHolder.getContext().setAuthentication(auth);

		var id = repository.findByOwner("testId").getFirst().getId();

		SurveyRequest request = new SurveyRequest(
			"서베이 수정 테스트 제목",
			"서베이 수정 테스트 설명",
			"{\"sections\":[]}"
		);


		CustomException exception = assertThrows(CustomException.class, () -> {
			surveyService.update(id, request, auth);
		});

		assertThat(exception.getErrorCode()).isEqualTo(NOT_AUTHORIZED);
	}

	@Test
	@DisplayName("설문 게시")
	void publish() {

		saveSurvey();

		var auth = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken("k2ngis", "testPassword2"));
		SecurityContextHolder.getContext().setAuthentication(auth);

		var id = repository.findByOwner("k2ngis").getFirst().getId();

		SurveyResponse response = surveyService.publish(id, auth);

		assertThat(StringUtils.isNotBlank(response.publishId())).isTrue();
		assertThat(StringUtils.isNotBlank(response.publishedAt())).isTrue();
	}

	@Test
	@DisplayName("설문 정보 삭제")
	void delete() {

		saveSurvey();

		var auth = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken("k2ngis", "testPassword2"));
		SecurityContextHolder.getContext().setAuthentication(auth);

		var id = repository.findByOwner("k2ngis").getFirst().getId();

		surveyService.delete(id, auth);

		CustomException exception = assertThrows(CustomException.class, () -> {
			surveyService.findById(id, auth);
		});

		assertThat(exception.getErrorCode()).isEqualTo(NOT_FOUND_SURVEY);
	}
}