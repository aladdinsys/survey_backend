package aladdinsys.aladdin_survey.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import jdk.javadoc.doclet.Doclet;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@OpenAPIDefinition(
        info = @Info(title = "관리자 페이지 API 명세서",
                description = "사용자 관리 및 설문조사 생성에 관한 API 명세서",
                version = "v1")
)

@Configuration
public class SwaggerConfig {

    private static final String BEARER_TOKEN_PREFIX = "Bearer";

    private GroupedOpenApi createGroupedOpenApi(String groupName, String... packagesToScan) {
        return GroupedOpenApi.builder()
                .group(groupName)
                .packagesToScan(packagesToScan)
                .build();
    }

    @Bean
    public OpenAPI openAPI() {
        String securityJwtName = "JWT";
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(securityJwtName);
        Components components = new Components()
                .addSecuritySchemes(securityJwtName, new SecurityScheme()
                        .name(securityJwtName)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme(BEARER_TOKEN_PREFIX)
                        .bearerFormat(securityJwtName));
        return new OpenAPI()
                .addSecurityItem(securityRequirement)
                .components(components);
    }

    @Bean
    public GroupedOpenApi surveyApi() {
        return createGroupedOpenApi("survey", "aladdinsys.aladdin_survey.domains.survey.controller");
    }

    @Bean
    public GroupedOpenApi userApi() {
        return createGroupedOpenApi("user", "aladdinsys.aladdin_survey.domains.user.controller");
    }


}