/* (C) 2023 */
package aladdinsys.lifelong_learning_survey.global.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import aladdinsys.lifelong_learning_survey.global.response.ErrorResponseBody;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {
  private static final Logger LOGGER = LoggerFactory.getLogger(CustomExceptionHandler.class);

  @ExceptionHandler
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponseBody handleCustomException(CustomException e) {
    LOGGER.error("CustomException", e);
    return ErrorResponseBody.of(e.getErrorCode());
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ErrorResponseBody handleInvalidCustomException(ConstraintViolationException e) {
    LOGGER.error("ConstraintViolationException", e);
    return ErrorResponseBody.of(HttpStatus.BAD_REQUEST, e.getMessage());
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<String> handleHttpRequestMethodNotSupportedException(
      HttpRequestMethodNotSupportedException e) {
    LOGGER.error("HttpRequestMethodNotSupportedException", e);
    return ResponseEntity.badRequest().body("해당 endpoint 를 찾지 못했습니다. method 와 url 을 확인해주세요");
  }

}
