package com.app.money_app_spring_oauth2_hexagonal.user.application;

import com.app.money_app_spring_oauth2_hexagonal.user.domain.dataTransferObjects.UserRequest;
import com.app.money_app_spring_oauth2_hexagonal.user.domain.services.UserService;
import org.springframework.stereotype.Component;

@Component
public class SaveUser {
    private final UserService userService;
    public SaveUser(UserService userService) {
        this.userService = userService;
    }
    public final String execute(UserRequest userRequest) {
        return userService.saveUser(userRequest);
    }
}
