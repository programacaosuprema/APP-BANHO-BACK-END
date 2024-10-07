package com.dip.danielaraujo.dip_project.exceptions;

public class ClientNotFoundException extends RuntimeException{
    public ClientNotFoundException() {
        super("No client was not found");
    }

    public ClientNotFoundException(String message) {
        super(message);
    }

    public ClientNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientNotFoundException(Throwable cause) {
        super(cause);
    }
}
