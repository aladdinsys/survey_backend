/* (C) 2024 AladdinSystem License */
package aladdinsys.aladdin_survey.global.controller;

import aladdinsys.aladdin_survey.domains.survey.dto.SurveyResponse;
import aladdinsys.aladdin_survey.domains.survey.service.SurveyService;
import aladdinsys.aladdin_survey.global.response.DataResponseBody;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/open-api")
@RequiredArgsConstructor
public class OpenApiController {

  private final SurveyService surveyService;

  @GetMapping("/vworld/coord/{address}")
  public DataResponseBody<Object> getVworldCoord(@PathVariable("address") String address) {

    final String apikey = "F79DF30C-7109-30C4-8E1F-1539EF5FC93D";

    String urlString =
        "https://api.vworld.kr/req/address?service=address&request=getCoord&format=json&crs=epsg:4326&key=%s&type=road&address=%s"
            .formatted(apikey, URLEncoder.encode(address, StandardCharsets.UTF_8));

    StringBuilder content = new StringBuilder();

    try (InputStreamReader in = new InputStreamReader(new URI(urlString).toURL().openStream())) {

      BufferedReader bin = new BufferedReader(in);
      String inputLine;

      while ((inputLine = bin.readLine()) != null) {
        content.append(inputLine);
      }

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    return DataResponseBody.of(content.toString());
  }

  @GetMapping("/survey/{publishId}")
  public DataResponseBody<SurveyResponse> getSurvey(@PathVariable("publishId") String publishId) {
    return DataResponseBody.of(surveyService.findByPublishId(publishId));
  }
}
