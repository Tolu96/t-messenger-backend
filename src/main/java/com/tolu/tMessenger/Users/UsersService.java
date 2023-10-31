package com.tolu.tMessenger.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public List<Users> getAllUsers() {
        return new ArrayList<>(usersRepository.findAll().stream().toList());
    }


    public Optional<Users> getUserById(UUID userId) {
        if (usersRepository.existsById(userId)) {
            return usersRepository.findById(userId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + userId + " does not exist");
        }
    }

    public Users createNewUser(Users newUser) {
        List<Users> sameUserName = usersRepository.findAll().stream().filter(c -> c.getUsername().equals(newUser.getUsername())).toList();
        if (sameUserName.isEmpty()) {
            return usersRepository.save(newUser);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username " + newUser.getUsername() + " already exists");
        }
    }

    public void deleteUser(UUID userId) {
        boolean exists = usersRepository.existsById(userId);
        if (!exists) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + userId + " does not exist");
        } else {
            usersRepository.deleteById(userId);
        }
    }

    @Transactional
    public void updateUser(UUID userId, Users usersDetails) {

        Users updatedUser = usersRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with " + "UserId " + userId + " does not exist"));

        String oldUsername = updatedUser.getUsername();
        String oldPassword = updatedUser.getPassword();

        String newUsername = usersDetails.getUsername();
        String newPassword = usersDetails.getPassword();


        if (newUsername != null) {
            if (newUsername.length() > 3 && !newUsername.equals(oldUsername)) {
                updatedUser.setUsername(newUsername);
            } else {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Could not change Username " + oldUsername + " into " + newUsername);
            }
        }

        if (newPassword != null) {
            if (newPassword.length() > 8 && !newPassword.equals(oldPassword)) {
                updatedUser.setPassword(newPassword);
            } else {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Could not Change Password");
            }
        }
    }
}