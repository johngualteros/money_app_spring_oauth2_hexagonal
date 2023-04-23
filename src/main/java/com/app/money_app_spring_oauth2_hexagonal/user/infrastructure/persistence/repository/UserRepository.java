package com.app.money_app_spring_oauth2_hexagonal.user.infrastructure.persistence.repository;

import com.app.money_app_spring_oauth2_hexagonal.user.infrastructure.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
* interface extends jpa for the queries to the database of User entity
* */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query(value = "SELECT * FROM USERS WHERE username = ?1", nativeQuery = true)
    public Optional<User> getUserByUsername(String username);

    @Query(value = "SELECT * FROM USERS WHERE email = ?1", nativeQuery = true)
    public Optional<User> getUserByEmail(String email);
}
