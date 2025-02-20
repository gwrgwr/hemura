package com.mullen.hemura.exceptions.session;

public class SessionNotFoundException extends RuntimeException {
    public SessionNotFoundException(String message) {
        super(message);
    }

    public SessionNotFoundException() {
        super("Session not found");
    }
}
