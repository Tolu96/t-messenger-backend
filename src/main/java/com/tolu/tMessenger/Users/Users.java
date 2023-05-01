package com.tolu.tMessenger.Users;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Users {
    private @Id
    @GeneratedValue long userId;
    private String userName;
    private String password;

    public Users(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
