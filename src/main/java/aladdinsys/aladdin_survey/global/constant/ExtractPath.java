/* (C) 2024 AladdinSystem License */
package aladdinsys.aladdin_survey.global.constant;

import lombok.Getter;

@Getter
public enum ExtractPath {
  OPEN_API("open-api"),
  AUTHENTICATION("auth"),
  ERROR("error"),
  SWAGGER_UI("swagger-ui"),
  SWAGGER_CONFIG("swagger-config"),
  V3_API_DOCS("v3/api-docs");
  ;

  private final String path;

  ExtractPath(String path) {
    this.path = path;
  }
}
