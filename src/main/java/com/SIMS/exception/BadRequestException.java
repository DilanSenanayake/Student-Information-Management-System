package com.SIMS.exception;

public class BadRequestException extends RuntimeException{

    private String message;

    /**
     * Custom exception for bad request.
     * Bad request is returned with 400 HTTP status code.
     *
     * @param message custom error message
     */
    public BadRequestException(String message) {
        super(message);
        this.message = message;
    }
}
