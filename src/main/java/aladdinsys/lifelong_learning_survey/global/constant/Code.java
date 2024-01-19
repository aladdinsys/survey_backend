/* (C) 2023 */
package aladdinsys.lifelong_learning_survey.global.constant;

import org.springframework.http.HttpStatus;

public interface Code {

  HttpStatus getHttpStatus();

  String getMessage();

  String getStatusName();

  Integer getStatusValue();
}
