package com.app.money_app_spring_oauth2_hexagonal.user.infrastructure.persistence.mapper;

import com.app.money_app_spring_oauth2_hexagonal.user.domain.records.UserResponse;
import com.app.money_app_spring_oauth2_hexagonal.user.infrastructure.persistence.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class EntityToResponse {
    /*
     * @return UserResponse
     * @Accessibility static
     * @Params User
     * mapper for convert the User to UserResponse
     * */
    public static UserResponse convertUserEntityToUserResponse(User user) {
        return new UserResponse(
                user.getName(),
                user.getUsername(),
                user.getEmail(),
                user.getPhone()
        );
    }
    /*
     * @return List<UserResponse>
     * @Accessibility static
     * @Params List<User>
     * mapper for convert the List<User> to List<UserResponse>
     * */
    public static List<UserResponse> convertListUserEntityToListUserResponse(
            List<User> listOfusers) {
        return listOfusers.stream()
                .map(user -> new UserResponse(
                        user.getName(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getPhone()
                )).collect(Collectors.toList());
    }
}
