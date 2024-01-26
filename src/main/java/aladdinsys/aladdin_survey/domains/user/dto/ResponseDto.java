/* (C) 2024 AladdinSystem License */
package aladdinsys.aladdin_survey.domains.user.dto;

import aladdinsys.aladdin_survey.domains.user.constant.Role;
import lombok.Builder;

@Builder
public record ResponseDto(String userId, String name, String email, String code, Role role) {}
