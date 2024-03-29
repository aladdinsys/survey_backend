package aladdinsys.aladdin_survey.domains.user.controller;

import aladdinsys.aladdin_survey.domains.auth.dto.SignInRequestDto;
import aladdinsys.aladdin_survey.domains.auth.dto.SignInResponseDto;
import aladdinsys.aladdin_survey.domains.auth.dto.SignUpRequestDto;
import aladdinsys.aladdin_survey.domains.auth.service.AuthenticationService;
import aladdinsys.aladdin_survey.domains.user.entity.User;
import aladdinsys.aladdin_survey.domains.user.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthenticationService authService;

    @Autowired
    EntityManager em;

    @Autowired
    UserRepository userRepository;

    private String token;
    private Long id;

    @BeforeEach
    void setUp() {
        SignUpRequestDto adminSignUpRequestDto = new SignUpRequestDto(
                "admin", "admin", "관리자", "ADMIN_CODE", "admin@aladdinsys.co.kr");
        authService.signUpAdmin(adminSignUpRequestDto);

        SignInResponseDto adminSignInResponse = authService.signIn(new SignInRequestDto("admin", "admin"));
        token = adminSignInResponse.accessToken();

        authService.signUp(new SignUpRequestDto("testId", "testPassword", "홍길동", "12345", "hongildong@aladdinsys.co.kr"));
        User testUser = userRepository.findByUserId("testId").orElseThrow(() -> new RuntimeException("User not found"));
        id = testUser.getId();

        em.clear();
    }

    @Test
    @DisplayName("전체 사용자 목록 조회")
    void getUsers() throws Exception {
        mockMvc.perform(get("/users")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").isArray())
                .andExpect(jsonPath("$.result[0].userId").value("admin"))
                .andExpect(jsonPath("$.result[?(@.userId == 'testId')]").exists())
                .andDo(print());
    }

    @Test
    @DisplayName("사용자 목록 조회")
    void getUser() throws Exception {
        mockMvc.perform(get("/users/" + id)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.userId").value("testId"))
                .andDo(print());
    }

    @Test
    @DisplayName("사용자 비밀번호 수정")
    void changePassword() throws Exception {
        mockMvc.perform(patch("/users/" + id + "/change-password")
                        .header("Authorization", "Bearer " + token)
                        .contentType("application/json")
                        .content("{\"password\": \"testPassword\", \"newPassword\": \"newPassword\"}"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.status").value("ACCEPTED"))
                .andDo(print());
    }

    @Test
    @DisplayName("사용자 삭제")
    void deleteUser() throws Exception {
        mockMvc.perform(delete("/users/" + id)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.status").value("NO_CONTENT"))
                .andDo(print());
    }
}