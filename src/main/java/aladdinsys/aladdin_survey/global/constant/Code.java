/* (C) 2023 AladdinSystem License */
package aladdinsys.aladdin_survey.global.constant;

import org.springframework.http.HttpStatus;

public interface Code {

  HttpStatus getHttpStatus();

  String getMessage();

  String getStatusName();

  Integer getStatusValue();
}
