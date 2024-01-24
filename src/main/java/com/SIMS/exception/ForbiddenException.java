package com.SIMS.exception;

public class ForbiddenException extends Exception{
  String message;

  /**
   * Custom exception for forbidden action.
   *
   * @param message custom error message
   */
  public ForbiddenException(String message) {
    super(message);
    this.message = message;
  }
}
