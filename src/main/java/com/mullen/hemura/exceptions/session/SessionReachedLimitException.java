package com.mullen.hemura.exceptions.session;

public class SessionReachedLimitException extends RuntimeException {
    public SessionReachedLimitException(String message) {
        super(message);
    }

    public SessionReachedLimitException() {
        super("Reached Session Limit per User");
    }
}
