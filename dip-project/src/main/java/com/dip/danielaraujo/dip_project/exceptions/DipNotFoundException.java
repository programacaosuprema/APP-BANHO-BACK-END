package com.dip.danielaraujo.dip_project.exceptions;

public class DipNotFoundException extends RuntimeException{
    public DipNotFoundException() {
        super("No dip was not found");
    }

    public DipNotFoundException(String message) {
        super(message);
    }

    public DipNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DipNotFoundException(Throwable cause) {
        super(cause);
    }
}
