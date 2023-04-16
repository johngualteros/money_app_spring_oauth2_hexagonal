package com.app.money_app_spring_oauth2_hexagonal.user.infrastructure.persistence.mapper;

import com.app.money_app_spring_oauth2_hexagonal.user.domain.dataTransferObjects.UserRequest;
import com.app.money_app_spring_oauth2_hexagonal.user.domain.enums.Status;
import com.app.money_app_spring_oauth2_hexagonal.user.infrastructure.persistence.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class RequestToEntityTest {
    private UserRequest userRequestTest;
    private UserRequest userRequestTest2;
    @BeforeEach
    void setUp() {
        userRequestTest = new UserRequest(
                "john",
                "gualteros",
                "johngua",
                "test@gmail.com",
                "defedefendje",
                "3227687632",
                Status.ACTIVE
        );
        userRequestTest2 = new UserRequest(
                "dani",
                "sanchez",
                "danisa",
                "test2@gmail.com",
                "defedefedef",
                "32276874356",
                Status.ACTIVE
        );
    }

    @Test
    void convertRequestToEntity() {
        User mappedUser = RequestToEntity
                .convertRequestToEntity(userRequestTest);
        String fullName = userRequestTest.getFirst_name()
                .concat(" ")
                .concat(userRequestTest.getLast_name());
        assertThat(fullName, is(equalTo(mappedUser.getName())));
    }

    @Test
    void convertListRequestToListEntity() {
        List<UserRequest> listOfUserRequest = new ArrayList<>();
        listOfUserRequest.add(userRequestTest);
        listOfUserRequest.add(userRequestTest2);
        List<User> listOfUser = RequestToEntity
                .convertListRequestToListEntity(listOfUserRequest);

        boolean validatedObject = validateEachObject(listOfUserRequest,listOfUser);
        Assertions.assertTrue(validatedObject);
    }

    public boolean validateEachObject(List<UserRequest> listUserRequest,
                                      List<User> listUser) {
        // in case of list user or userRequest not is equals return false
        if ( listUserRequest.size() != listUser.size() ) {
            return false;
        }
        int lengthShouldBeAssert = listUser.size();
        int actualAssertions = 0;

        for(UserRequest userRequest : listUserRequest) {
            for(User user :  listUser) {
                if(customMatcherName(userRequest, user)) {
                    actualAssertions++;
                    break;
                }
            }
        }
        return lengthShouldBeAssert == actualAssertions;
    }

    public boolean customMatcherName(UserRequest userRequest, User user) {
        return user.getName()
                .equalsIgnoreCase(userRequest.getFirst_name() + " " + userRequest.getLast_name());
    }
}