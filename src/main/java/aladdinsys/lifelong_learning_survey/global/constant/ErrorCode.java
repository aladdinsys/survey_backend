/* (C) 2023 */
package aladdinsys.lifelong_learning_survey.global.constant;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode implements Code {
  DUPLICATE_USERID(BAD_REQUEST, "이미 존재 하는 아이디 입니다."),

  INVALID_EMAIL(BAD_REQUEST, "유효하지 않은 Email 주소 입니다."),
  INVALID_ID_OR_PASSWORD(BAD_REQUEST, "아이디 또는 비밀번호가 틀렸습니다."),

  INVALID_JWT_TOKEN(BAD_REQUEST, "JWT 토큰이 만료되거나 유효하지 않습니다."),
  INVALID_TOKEN_FORMAT(BAD_REQUEST, "유효하지 않은 토큰 형식입니다."),

  EXPIRED_JWT_TOKEN(BAD_REQUEST, "JWT 토큰이 만료되었습니다."),

  NOT_FOUND_USER(BAD_REQUEST, "해당 회원이 존재하지 않습니다.");

  private final HttpStatus httpStatus;
  private final String message;

  @Override
  public HttpStatus getHttpStatus() {
    return this.httpStatus;
  }

  @Override
  public String getMessage() {
    return this.message;
  }

  @Override
  public String getStatusName() {
    return this.httpStatus.name();
  }

  @Override
  public Integer getStatusValue() {
    return this.httpStatus.value();
  }
}
