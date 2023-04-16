package com.app.money_app_spring_oauth2_hexagonal.user.domain.records;

public record UserResponse(String name,
                           String username,
                           String email,
                           String phone) {
}
