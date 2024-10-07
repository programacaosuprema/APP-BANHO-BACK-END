package com.dip.danielaraujo.dip_project.exceptions;

public class ValidateClientException extends RuntimeException{
    public ValidateClientException() {
        super("The string cannot be empty");
    }

    public ValidateClientException(String message) {
        super(message);
    }

    public ValidateClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidateClientException(Throwable cause) {
        super(cause);
    }
}
