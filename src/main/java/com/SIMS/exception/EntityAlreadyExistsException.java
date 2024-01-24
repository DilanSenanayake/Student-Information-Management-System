package com.SIMS.exception;

public class EntityAlreadyExistsException extends RuntimeException {

  private String message;
  private Object data;

  /**
   * Custom exception for entity already exists.
   * Existing entity is returned with 409 HTTP status code.
   *
   * @param message custom error message
   * @param data existing entity
   */
  public EntityAlreadyExistsException(String message, Object data) {
    super(message);
    this.message = message;
    this.data = data;
  }

  @Override
  public String getMessage() {
    return message;
  }


  public Object getData() {
    return data;
  }

}
