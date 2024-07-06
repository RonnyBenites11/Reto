package com.civa.futbolista.utils;

public class ErrorNotFoundException extends RuntimeException {
    public ErrorNotFoundException() {
        super();
    }

    public ErrorNotFoundException(String message) {
        super(message);
    }

    public ErrorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ErrorNotFoundException(Throwable cause) {
        super(cause);
    }
}