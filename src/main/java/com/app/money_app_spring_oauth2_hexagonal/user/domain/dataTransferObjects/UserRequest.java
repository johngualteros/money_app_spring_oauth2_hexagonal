package com.app.money_app_spring_oauth2_hexagonal.user.domain.dataTransferObjects;

import com.app.money_app_spring_oauth2_hexagonal.user.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class UserRequest {
    private final String first_name;
    private final String last_name;
    private final String username;
    private final String email;
    private final String password;
    private final String phone;
    private final Status status;
}