package com.app.money_app_spring_oauth2_hexagonal.user.application;

import com.app.money_app_spring_oauth2_hexagonal.user.domain.records.UserResponse;
import com.app.money_app_spring_oauth2_hexagonal.user.domain.services.UserService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllUsers {
    private final UserService userService;
    public GetAllUsers(UserService userService) {
        this.userService = userService;
    }
    public final List<UserResponse> execute() {
        return userService.getAllUsers();
    }
}
