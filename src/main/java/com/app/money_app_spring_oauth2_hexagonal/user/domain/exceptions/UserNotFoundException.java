package com.app.money_app_spring_oauth2_hexagonal.user.domain.exceptions;

public class UserNotFoundException extends RuntimeException {
    // Constructor receive the message error
    public UserNotFoundException(String message) {
        super(message);
    }
}
