package com.app.money_app_spring_oauth2_hexagonal.user.domain.exceptions;

public class EmailAlreadyExistsException extends RuntimeException {
    // Constructor receive the message error
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
