package com.mullen.hemura.exceptions.user;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
    public UserAlreadyExistsException() {
        super("User already exists");
    }
}
