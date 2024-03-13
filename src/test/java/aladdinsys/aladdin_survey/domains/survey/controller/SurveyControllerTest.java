package aladdinsys.aladdin_survey.domains.survey.controller;

import aladdinsys.aladdin_survey.domains.auth.dto.ApiKeyResponseDto;
import aladdinsys.aladdin_survey.domains.auth.dto.SignInRequestDto;
import aladdinsys.aladdin_survey.domains.auth.dto.SignInResponseDto;
import aladdinsys.aladdin_survey.domains.auth.dto.SignUpRequestDto;
import aladdinsys.aladdin_survey.domains.auth.service.ApiKeyService;
import aladdinsys.aladdin_survey.domains.auth.service.AuthenticationService;
import aladdinsys.aladdin_survey.domains.survey.entity.Spatial;
import aladdinsys.aladdin_survey.domains.survey.entity.Survey;
import aladdinsys.aladdin_survey.domains.survey.repository.SurveyRepository;
import aladdinsys.aladdin_survey.domains.user.entity.User;
import aladdinsys.aladdin_survey.domains.user.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SurveyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthenticationService authService;

    @Autowired
    EntityManager em;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SurveyRepository surveyRepository;

    @Autowired
    ApiKeyService apiKeyService;

    private String token;
    private Long id;
    private Long surveyId;


    private Long createAndSaveSurvey(String title, String description, String content, Spatial center, String owner) {
        Survey survey = new Survey(title, description, content, center, owner);
        survey = surveyRepository.save(survey);
        return survey.getId();
    }

    @BeforeEach
    void setUp() {
        authService.signUp(new SignUpRequestDto("user1", "password", "User Name", "USER_CODE", "user@aladdinsys.com"));
        SignInResponseDto userSignInResponse = authService.signIn(new SignInRequestDto("user1", "password"));
        token = userSignInResponse.accessToken();

        User testUser = userRepository.findByUserId("user1").orElseThrow(() -> new RuntimeException("User not found"));
        id = testUser.getId();

        Spatial center = new Spatial(126.784587, 37.645143);

        surveyId = createAndSaveSurvey("Title 1", "Description 1", "Content 1", center, "user1");
        createAndSaveSurvey("Title 2", "Description 2", "Content 2", center, "user2");
        createAndSaveSurvey("Title 3", "Description 3", "Content 3", center, "user3");
        createAndSaveSurvey("Title 4", "Description 4", "Content 3", center, "user4");

        em.clear();
    }

    @Test
    @DisplayName("내 설문지 목록 조회")
    void getFindOwn() throws Exception {
        mockMvc.perform(get("/surveys/find-own")
                        .header("Authorization", "Bearer " + token)
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result[0].title").value("Title 1"))
                .andExpect(jsonPath("$.result[0].description").value("Description 1"))
                .andExpect(jsonPath("$.result[0].content").value("Content 1"))
                .andDo(print());
    }

    @Test
    @DisplayName("전체 설문지 목록 조회")
    void getFindAll() throws Exception {
        mockMvc.perform(get("/surveys")
                        .header("Authorization", "Bearer " + token)
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result[0].title").value("Title 1"))
                .andExpect(jsonPath("$.result[1].title").value("Title 2"))
                .andExpect(jsonPath("$.result[2].title").value("Title 3"))
                .andDo(print());
    }

    @Test
    @DisplayName("설문지 By ID 조회")
    void getFindById() throws Exception {
        mockMvc.perform(get("/surveys/" + surveyId)
                        .header("Authorization", "Bearer " + token)
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.title").value("Title 1"))
                .andExpect(jsonPath("$.result.description").value("Description 1"))
                .andExpect(jsonPath("$.result.content").value("Content 1"))
                .andDo(print());
    }

    @Test
    @DisplayName("존재하지 않는 설문지 조회")
    void getSurveyNotFound() throws Exception {
        Long nonExistingSurveyId = 99999L;
        mockMvc.perform(get("/surveys/" + nonExistingSurveyId)
                        .header("Authorization", "Bearer " + token)
                        .contentType("application/json"))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andDo(print());
    }

    @Test
    @DisplayName("권한이 없는 사용자의 설문지 조회")
    void getSurveyUnauthorized() throws Exception {
        mockMvc.perform(get("/surveys/" + surveyId)
                        .contentType("application/json"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.status").value("UNAUTHORIZED"))
                .andDo(print());
    }

    @Test
    @DisplayName("설문지 생성")
    void post() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/surveys")
                        .header("Authorization", "Bearer " + token)
                        .contentType("application/json")
                        .content("{\"title\":\"Title 5\",\"description\":\"Description\", \"center\":{\"x\":123.5,\"y\":38.34441},\"content\":\"[]\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.owner").value("user1"))
                .andDo(print());
    }

    @Test
    @DisplayName("잘못된 형식의 설문지 생성 요청")
    void createSurveyWithInvalidData() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/surveys")
                        .header("Authorization", "Bearer " + token)
                        .contentType("application/json")
                        .content("{\"title\": \"\", \"description\": \"\", \"center\":{\"x\":123.5,\"y\":38.34441}, \"content\": \"\"}"))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    @DisplayName("설문지 수정")
    void patch() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/surveys/" + surveyId)
                        .header("Authorization", "Bearer " + token)
                        .contentType("application/json")
                        .content("{\"title\": \"Title 5\", \"description\": \"Description 5\", \"center\":{\"x\":123.5,\"y\":38.34441}, \"content\": \"[]\"}"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.status").value("ACCEPTED"))
                .andDo(print());

        Survey updatedSurvey = surveyRepository.findById(surveyId).orElseThrow();
        assertEquals("Title 5", updatedSurvey.getTitle());
        assertEquals("Description 5", updatedSurvey.getDescription());
        assertEquals("[]", updatedSurvey.getContent());
    }

    @Test
    @DisplayName("본인 설문지 uuid 생성")
    void publish() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/surveys/" + surveyId + "/publish")
                        .header("Authorization", "Bearer " + token)
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.publishId").isNotEmpty())
                .andDo(print());
        Survey publishedSurvey = surveyRepository.findById(surveyId).orElseThrow();
        assertNotNull(publishedSurvey.getPublishId());
    }

    @Test
    @DisplayName("설문지 생성 및 uuid 생성")
    void testPublish() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/surveys/publish")
                        .header("Authorization", "Bearer " + token)
                        .contentType("application/json")
                        .content("{\"title\": \"Title 6\", \"description\": \"Description 6\", \"center\":{\"x\":123.5,\"y\":38.34441}, \"content\": \"[]\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.publishId").isNotEmpty())
                .andExpect(jsonPath("$.result.owner").value("user1"))
                .andDo(print());
    }

    @Test
    @DisplayName("설문지 삭제")
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/surveys/" + surveyId)
                        .header("Authorization", "Bearer " + token)
                        .contentType("application/json"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.status").value("NO_CONTENT"))
                .andDo(print());
    }

}