package com.SIMS.exception;

public class EntityNotFoundException extends RuntimeException {

  String message;

  /**
   * Custom exception for entity not found.
   *
   * @param message custom error message
   */
  public EntityNotFoundException(String message) {
    super(message);
    this.message = message;

  }

}
