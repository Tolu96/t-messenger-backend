package com.tolu.tMessenger.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping()
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }

    @GetMapping(path = "{userId}")
    public Optional<Users> getUserById(@PathVariable("userId") UUID userId) {
        return usersService.getUserById(userId);
    }

    @PostMapping(value = "/", produces = "application/json")
    public Users createNewUser(@RequestBody Users newUser) {
        return usersService.createNewUser(newUser);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") UUID userId) {
        usersService.deleteUser(userId);
    }

    @PutMapping(path = "{userId}")
    public void updateUser(@PathVariable("userId") UUID userId, @RequestBody Users usersDetails) {
        usersService.updateUser(userId, usersDetails);
    }

}


