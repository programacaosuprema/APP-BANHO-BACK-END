package com.dip.danielaraujo.dip_project.exceptions;

public class InvalidDataFromClientException extends RuntimeException{
    public InvalidDataFromClientException() {
        super("The string cannot be empty");
    }

    public InvalidDataFromClientException(String message) {
        super(message);
    }

    public InvalidDataFromClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDataFromClientException(Throwable cause) {
        super(cause);
    }
}
