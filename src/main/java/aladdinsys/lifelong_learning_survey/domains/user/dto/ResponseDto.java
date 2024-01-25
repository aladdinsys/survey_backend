/* (C) 2024 AladdinSystem License */
package aladdinsys.lifelong_learning_survey.domains.user.dto;

import aladdinsys.lifelong_learning_survey.domains.user.constant.Role;
import lombok.Builder;

@Builder
public record ResponseDto(String userId, String name, String email, String code, Role role) {}
