package com.SIMS.exception;

import org.springframework.http.HttpStatus;

public class ApiError {

  private HttpStatus code;
  private String message;
  private Object data;

  private ApiError() {
  }

  /**
   * Create API Error with HTTPCode.
   *
   * @param code HttpStatus
   */
  public ApiError(HttpStatus code) {
    this();
    this.code = code;
    this.data = null;
  }

  /**
   * Creates ApiError with HTTPCode and Exception.
   *
   * @param code HttpStatus
   * @param ex   exception
   */
  public ApiError(HttpStatus code, Throwable ex) {
    this();
    this.code = code;
    this.message = "Unexpected error";
    this.data = null;
  }

  /**
   * Created ApiError with custom message and exception.
   *
   * @param code    HttpStatus
   * @param message custom message
   * @param ex      exception
   */
  public ApiError(HttpStatus code, String message, Throwable ex) {
    this();
    this.code = code;
    this.message = message;
    this.data = null;
  }

  /**
   * Created ApiError with custom message and data.
   *
   * @param code HttpStatus
   * @param message custom message
   * @param data Data returned
   */
  public ApiError(HttpStatus code, String message, Object data) {
    this.code = code;
    this.message = message;
    this.data = data;
  }

  public HttpStatus getCode() {
    return code;
  }

  public void setCode(HttpStatus code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }
}
