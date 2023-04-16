package com.app.money_app_spring_oauth2_hexagonal.user.infrastructure.persistence.mapper;

import com.app.money_app_spring_oauth2_hexagonal.user.domain.enums.Status;
import com.app.money_app_spring_oauth2_hexagonal.user.domain.records.UserResponse;
import com.app.money_app_spring_oauth2_hexagonal.user.infrastructure.persistence.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class EntityToResponseTest {
    private static User userTest;
    private static User userTest2;
    @BeforeEach
    void setUp() {
        userTest = new User(
                "john gualteros",
                "joohngua",
                "test@gmail.com",
                "dedefefrgrtg",
                "3227680732",
                Status.ACTIVE
        );
        userTest2 = new User(
                "juan pablo",
                "juanpa",
                "test2@gmail.com",
                "dedefe34dfew",
                "3137570435",
                Status.ACTIVE
        );
    }

    @Test
    void convertUserEntityToUserResponse() {
        UserResponse userResponse = EntityToResponse
                .convertUserEntityToUserResponse(userTest);
        assertNotNull(userResponse);
    }

    @Test
    void convertListUserEntityToListUserResponse() {
        List<User> listOfUsers = new ArrayList<>();
        listOfUsers.add(userTest);
        listOfUsers.add(userTest2);
        List<UserResponse> listOfUsersResponse = EntityToResponse
                .convertListUserEntityToListUserResponse(listOfUsers);
        assertThat(listOfUsers.size(), is(equalTo(listOfUsersResponse.size())));
    }
}