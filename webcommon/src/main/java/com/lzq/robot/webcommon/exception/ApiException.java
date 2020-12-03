package com.lzq.robot.webcommon.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiException extends RuntimeException {

  private HttpStatus httpStatus;
  private Object data;

  public ApiException(HttpStatus httpStatus, String msg) {
    super(msg);
    this.httpStatus = httpStatus;
  }
  public ApiException(HttpStatus httpStatus, String msg, Object data) {
    super(msg);
    this.httpStatus = httpStatus;
    this.data = data;
  }
}
