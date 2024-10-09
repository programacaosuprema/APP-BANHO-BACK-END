package com.dip.danielaraujo.dip_project.exceptions;

public class InvalidDataException extends RuntimeException{
    public InvalidDataException() {
        super("The string cannot be empty");
    }

    public InvalidDataException(String message) {
        super(message);
    }

    public InvalidDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDataException(Throwable cause) {
        super(cause);
    }
}
