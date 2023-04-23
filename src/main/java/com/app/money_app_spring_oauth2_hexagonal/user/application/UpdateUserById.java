package com.app.money_app_spring_oauth2_hexagonal.user.application;

import com.app.money_app_spring_oauth2_hexagonal.user.domain.dataTransferObjects.UserRequest;
import com.app.money_app_spring_oauth2_hexagonal.user.domain.records.UserResponse;
import com.app.money_app_spring_oauth2_hexagonal.user.domain.services.UserService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UpdateUserById {
    private final UserService userService;
    public UpdateUserById(UserService userService) {
        this.userService = userService;
    }
    public final Optional<UserResponse> execute(String id, UserRequest userRequest) {
        return userService.updateUserById(id, userRequest);
    }
}
