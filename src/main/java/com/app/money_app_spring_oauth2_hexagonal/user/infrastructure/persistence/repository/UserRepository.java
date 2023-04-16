package com.app.money_app_spring_oauth2_hexagonal.user.infrastructure.persistence.repository;

import com.app.money_app_spring_oauth2_hexagonal.user.infrastructure.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
* interface extends jpa for the queries to the database of User entity
* */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
