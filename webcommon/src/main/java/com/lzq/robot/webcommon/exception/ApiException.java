package com.lzq.robot.webcommon.exception;

import lombok.Data;

@Data
public class ApiException extends RuntimeException {

  private int code;
  private Object data;

  public ApiException(int code, String msg) {
    super(msg);
    this.code = code;
  }
  public ApiException(int code, String msg, Object data) {
    super(msg);
    this.code = code;
    this.data = data;
  }
}
