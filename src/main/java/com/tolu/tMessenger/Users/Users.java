package com.tolu.tMessenger.Users;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users {
    private @Id
    @GeneratedValue(strategy = GenerationType.UUID) UUID userId;
    private String username;
    private String password;

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
