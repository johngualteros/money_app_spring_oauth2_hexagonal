package com.app.money_app_spring_oauth2_hexagonal.user.domain.exceptions;

public class UserNameAlreadyExistsException extends RuntimeException{
    // Constructor receive the message error
    public UserNameAlreadyExistsException(String message) {
        super(message);
    }
}
