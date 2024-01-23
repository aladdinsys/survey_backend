/* (C) 2024 */
package aladdinsys.lifelong_learning_survey.domains.user.constant;

import lombok.Getter;

@Getter
public enum Role {
  ADMIN("ROLE_ADMIN"),
  USER("ROLE_USER");

  private final String value;

  Role(String value) {
    this.value = value;
  }
}
