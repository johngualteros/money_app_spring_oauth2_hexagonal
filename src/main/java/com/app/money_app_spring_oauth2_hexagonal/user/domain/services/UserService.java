package com.app.money_app_spring_oauth2_hexagonal.user.domain.services;

import com.app.money_app_spring_oauth2_hexagonal.user.domain.dataTransferObjects.UserRequest;
import com.app.money_app_spring_oauth2_hexagonal.user.domain.enums.Status;
import com.app.money_app_spring_oauth2_hexagonal.user.domain.exceptions.EmailAlreadyExistsException;
import com.app.money_app_spring_oauth2_hexagonal.user.domain.exceptions.UserNameAlreadyExistsException;
import com.app.money_app_spring_oauth2_hexagonal.user.domain.exceptions.UserNotFoundException;
import com.app.money_app_spring_oauth2_hexagonal.user.domain.records.UserResponse;
import com.app.money_app_spring_oauth2_hexagonal.user.infrastructure.persistence.entity.User;
import com.app.money_app_spring_oauth2_hexagonal.user.infrastructure.persistence.mapper.EntityToResponse;
import com.app.money_app_spring_oauth2_hexagonal.user.infrastructure.persistence.mapper.RequestToEntity;
import com.app.money_app_spring_oauth2_hexagonal.user.infrastructure.persistence.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*
     * method for get the users of database
     * @return List<UserResponse>
     * */
    public List<UserResponse> getAllUsers() {
        List<User> listOfUsers = userRepository.findAll();
        return EntityToResponse.convertListUserEntityToListUserResponse(listOfUsers);
    }

    /*
     * method for get a single user by id
     * @return Optional<User> or UserNameNotFoundException
     * @params String id
     * */
    public Optional<UserResponse> getUserById(String id) throws UserNotFoundException {
        Optional<User> userFound = userRepository.findById(id);
        return userFound.map(EntityToResponse::convertUserEntityToUserResponse).map(Optional::of).orElseThrow(() -> new UserNotFoundException("the user not found by id: %s".formatted(id)));
    }

    /*
     * method for save a new user in the database
     * @return String
     * @params UserRequest or EmailAlreadyExistException or UserNameAlreadyExistException
     * */
    public String saveUser(UserRequest userRequest) throws EmailAlreadyExistsException, UserNameAlreadyExistsException {
        boolean notExistsByEmail = this.validateIfExistsByEmail(userRequest.getEmail());
        boolean notExistsByUsername = this.validateIfExistsByUsername(userRequest.getUsername());
        if (!notExistsByEmail) {
            throw new EmailAlreadyExistsException("the user already exists by this email");
        }
        if (!notExistsByUsername) {
            throw new UserNameAlreadyExistsException("the user already exists by this username");
        }
        User userForSave = RequestToEntity.convertRequestToEntity(userRequest);
        userRepository.save(userForSave);
        return "the user: %s saved successfully".formatted(userForSave.getName());
    }
    /*
    * method for update user by id
    * @return Optional<UserResponse> or UserNotFoundException
    * @params String id, UserRequest userRequest
    * */
    public Optional<UserResponse> updateUserById(String id, UserRequest userRequest) throws UserNotFoundException{
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            user.get().setName(userRequest.getFirst_name().concat(" ").concat(userRequest.getLast_name()));
            user.get().setUsername(userRequest.getUsername());
            user.get().setEmail(userRequest.getEmail());
            user.get().setPhone(userRequest.getPhone());
            userRepository.save(user.get());
        }
        return Optional.ofNullable(user
                .map(EntityToResponse::convertUserEntityToUserResponse)
                .orElseThrow(() -> new UserNotFoundException("user not found by id %s".formatted(id))));
    }
    /*
     * method for active user state by id
     * @return boolean
     * @params String id
     * */
    public boolean activeUser(String id) {
        return changeStatusById(id, Status.INACTIVE, Status.ACTIVE);
    }

    /*
     * method for inactive user state by id
     * @return boolean
     * @params String id
     * */
    public boolean inactiveUser(String id) {
        return changeStatusById(id, Status.ACTIVE, Status.INACTIVE);
    }

    /*
     * method for validate if user exists by email
     * @return boolean
     * @params String email
     * */
    private boolean validateIfExistsByEmail(String email) {
        Optional<User> userFound = userRepository.getUserByEmail(email);
        // return true if the user is not found and else if it already exists
        return userFound.isEmpty();
    }

    /*
     * method for validate if the user exists by username
     * @return boolean
     * @params String username
     * */
    private boolean validateIfExistsByUsername(String username) {
        Optional<User> userFound = userRepository.getUserByUsername(username);
        // return true if the user is not found and else if it already exists
        return userFound.isEmpty();
    }

    /*
     * method for change status by id
     * @return boolean
     * @params String id, Status status, Status statusToChange
     * */
    private boolean changeStatusById(String id, Status status, Status statusToChange) {
        Optional<User> userFound = userRepository.findById(id);
        if (userFound.isPresent() && userFound.get().getStatus().equals(status)) {
            userFound.get().setStatus(statusToChange);
            userRepository.save(userFound.get());
            return true;
        }
        return false;
    }
}
