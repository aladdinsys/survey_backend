package aladdinsys.aladdin_survey.domains.survey.controller;

import aladdinsys.aladdin_survey.domains.auth.dto.SignInRequestDto;
import aladdinsys.aladdin_survey.domains.auth.dto.SignInResponseDto;
import aladdinsys.aladdin_survey.domains.auth.dto.SignUpRequestDto;
import aladdinsys.aladdin_survey.domains.auth.service.AuthenticationService;
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

    private String token;
    private Long id;
    private Long surveyId;

    private Long createAndSaveSurvey(String title, String description, String content, String owner) {
        Survey survey = new Survey(title, description, content, owner);
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

        surveyId = createAndSaveSurvey("Title 1", "Description 1", "Content 1", "user1");
        createAndSaveSurvey("Title 2", "Description 2", "Content 2", "user2");
        createAndSaveSurvey("Title 3", "Description 3", "Content 3", "user3");
        createAndSaveSurvey("Title 4", "Description 4", "Content 3", "user4");

        em.clear();
    }

    @Test
    @Order(1)
    @DisplayName("내 설문지 목록 조회")
    void getFindOwn() throws Exception {
        mockMvc.perform(get("/api/surveys/find-own")
                        .header("Authorization", "Bearer " + token)
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result[0].title").value("Title 1"))
                .andExpect(jsonPath("$.result[0].description").value("Description 1"))
                .andExpect(jsonPath("$.result[0].content").value("Content 1"))
                .andDo(print());
    }

    @Test
    @Order(2)
    @DisplayName("전체 설문지 목록 조회")
    void getFindAll() throws Exception {
        mockMvc.perform(get("/api/surveys")
                        .header("Authorization", "Bearer " + token)
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result[0].title").value("Title 1"))
                .andExpect(jsonPath("$.result[1].title").value("Title 2"))
                .andExpect(jsonPath("$.result[2].title").value("Title 3"))
                .andDo(print());
    }

    @Test
    @Order(3)
    @DisplayName("설문지 By ID 조회")
    void getFindById() throws Exception {
        mockMvc.perform(get("/api/surveys/" + surveyId)
                        .header("Authorization", "Bearer " + token)
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.title").value("Title 1"))
                .andExpect(jsonPath("$.result.description").value("Description 1"))
                .andExpect(jsonPath("$.result.content").value("Content 1"))
                .andDo(print());
        System.out.println("id" + id);
    }

    @Test
    @Order(4)
    @DisplayName("설문지 생성")
    void post() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/surveys")
                        .header("Authorization", "Bearer " + token)
                        .contentType("application/json")
                        .content("{\"title\": \"Title 4\", \"description\": \"Description 4\", \"content\": \"Content 4\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.owner").value("user1"))
                .andDo(print());
    }

    @Test
    @Order(5)
    @DisplayName("설문지 수정")
    void patch() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/api/surveys/" + surveyId)
                        .header("Authorization", "Bearer " + token)
                        .contentType("application/json")
                        .content("{\"title\": \"Title 5\", \"description\": \"Description 5\", \"content\": \"Content 5\"}"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.status").value("ACCEPTED"))
                .andDo(print());

        Survey updatedSurvey = surveyRepository.findById(surveyId).orElseThrow();
        assertEquals("Title 5", updatedSurvey.getTitle());
        assertEquals("Description 5", updatedSurvey.getDescription());
        assertEquals("Content 5", updatedSurvey.getContent());
    }

    @Test
    @Order(6)
    @DisplayName("본인 설문지 uuid 생성")
    void publish() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/api/surveys/" + surveyId + "/publish")
                        .header("Authorization", "Bearer " + token)
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.publishId").isNotEmpty())
                .andDo(print());
        Survey publishedSurvey = surveyRepository.findById(surveyId).orElseThrow();
        assertNotNull(publishedSurvey.getPublishId());
    }

    @Test
    @Order(7)
    @DisplayName("설문지 생성 및 uuid 생성")
    void testPublish() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/api/surveys/publish")
                        .header("Authorization", "Bearer " + token)
                        .contentType("application/json")
                        .content("{\"title\": \"Title 6\", \"description\": \"Description 6\", \"content\": \"Content 6\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.publishId").isNotEmpty())
                .andExpect(jsonPath("$.result.owner").value("user1"))
                .andDo(print());
    }

    @Test
    @Order(8)
    @DisplayName("설문지 삭제")
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/surveys/" + surveyId)
                        .header("Authorization", "Bearer " + token)
                        .contentType("application/json"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.status").value("NO_CONTENT"))
                .andDo(print());
    }
}