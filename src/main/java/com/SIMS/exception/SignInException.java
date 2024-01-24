package com.SIMS.exception;

public class SignInException extends RuntimeException {

    private String message;

    /**
     * Custom exception for sign-in failure.
     * This exception can be used to represent a failed sign-in attempt.
     *
     * @param message custom error message
     */
    public SignInException(String message) {
        super(message);
        this.message = message;
    }
}
