package com.tolu.tMessenger.Users;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users {
    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) long userId;
    private String username;
    private String password;

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
