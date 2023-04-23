package com.app.money_app_spring_oauth2_hexagonal.user.application;

import com.app.money_app_spring_oauth2_hexagonal.user.domain.services.UserService;
import org.springframework.stereotype.Component;

@Component
public class InactiveUser {
    private final UserService userService;
    public InactiveUser(UserService userService) {
        this.userService = userService;
    }
    public final boolean execute(String id) {
        return userService.inactiveUser(id);
    }
}
