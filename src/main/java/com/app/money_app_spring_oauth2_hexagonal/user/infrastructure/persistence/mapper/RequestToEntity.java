package com.app.money_app_spring_oauth2_hexagonal.user.infrastructure.persistence.mapper;

import com.app.money_app_spring_oauth2_hexagonal.user.domain.dataTransferObjects.UserRequest;
import com.app.money_app_spring_oauth2_hexagonal.user.infrastructure.persistence.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class RequestToEntity {

    /*
    * @return User
    * @Accessibility static
    * @Params UserRequest
    * mapper for convert the UserRequest to UserEntity
    * */
    public static User convertRequestToEntity(UserRequest userRequest) {
        return new User(
                userRequest.getFirst_name() + " " + userRequest.getLast_name(),
                userRequest.getUsername(),
                userRequest.getEmail(),
                userRequest.getPassword(),
                userRequest.getPhone(),
                userRequest.getStatus()
        );
    }

    /*
     * @return List<User>
     * @Accessibility static
     * @Params List<UserRequest>
     * mapper for convert the List<UserRequest> to List<UserEntity>
     * */
    public static List<User> convertListRequestToListEntity(List<UserRequest> userRequests) {
        return userRequests.stream()
                .map(userRequest -> new User(
                        userRequest.getFirst_name() + " " +userRequest.getLast_name(),
                        userRequest.getUsername(),
                        userRequest.getEmail(),
                        userRequest.getPassword(),
                        userRequest.getPhone(),
                        userRequest.getStatus()
                ))
                .collect(Collectors.toList());
    }
}
