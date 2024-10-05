package com.dip.danielaraujo.dip_project.exceptions;

public class EmptyStringException extends RuntimeException{
    public EmptyStringException() {
        super("The string cannot be empty");
    }

    public EmptyStringException(String message) {
        super(message);
    }

    public EmptyStringException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyStringException(Throwable cause) {
        super(cause);
    }
}
