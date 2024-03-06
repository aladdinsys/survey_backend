/* (C) 2024 AladdinSystem License */
package aladdinsys.aladdin_survey.global.constant;

import lombok.Getter;

@Getter
public enum AuthenticationPath {
  USER("users"),
  SURVEY("surveys"),
  ;

  private final String path;

  AuthenticationPath(String path) {
    this.path = path;
  }
}
