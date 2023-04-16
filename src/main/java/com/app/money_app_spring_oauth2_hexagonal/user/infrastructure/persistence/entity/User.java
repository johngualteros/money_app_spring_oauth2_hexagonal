package com.app.money_app_spring_oauth2_hexagonal.user.infrastructure.persistence.entity;

import com.app.money_app_spring_oauth2_hexagonal.user.domain.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

// lombok library
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

// persistence annotations
@Entity
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    @NotNull(message = "the name field is required")
    @Size(min = 3, message = "the name must have 3 characters min")
    private String name;

    @Column
    @NotNull(message = "the username field is required")
    @Size(min = 3, message = "the username  must have 3 characters min")
    private String username;

    @Column
    @NotNull(message = "the email field is required")
    @Email(message = "the email entered not has the correct format")
    private String email;

    @Column
    @NotNull
    private String password;

    @Column
    @NotNull(message = "the phone field is required")
    @Pattern(regexp = "(^[0-9]+)", message = "the phone can only contains numbers")
    private String phone;

    @Column
    @Enumerated(EnumType.STRING)
    // the status by default is active
    private Status status = Status.ACTIVE;

    // constructor with not id

    public User(String name, String username, String email, String password, String phone, Status status) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.status = status;
    }
}

