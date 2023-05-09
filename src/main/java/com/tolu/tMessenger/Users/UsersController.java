package com.tolu.tMessenger.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping(path = "{userId}")
    public Optional<Users> getUserById(@PathVariable("userId") Long userId) {
        return usersService.getUserById(userId);
    }

    @PostMapping(value = "/", produces = "application/json")
    public Users createNewUser(@RequestBody Users newUser) {
        return usersService.createNewUser(newUser);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        usersService.deleteUser(userId);
    }

    @PutMapping(path = "{userId}")
    public void updateUser(@PathVariable("userId") Long userId, @RequestBody Users usersDetails) {
        usersService.updateUser(userId, usersDetails);
    }

}


