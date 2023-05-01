package com.tolu.tMessenger.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping("/")
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }

    @PostMapping("/users")
    public Users createNewUser(Users newUser) {
        return usersService.createNewUser(newUser);
    }

}


