package com.tolu.tMessenger.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public Optional<Users> getUserById(Long userId) {
        if (usersRepository.existsById(userId)) {
            return usersRepository.findById(userId);
        } else {
            throw new IllegalArgumentException("User with id " + userId + " does not exist");
        }
    }

    public Users createNewUser(Users newUser) {
        List<Users> sameUserName =
                usersRepository.findAll().stream().filter(c -> c.getUsername().equals(newUser.getUsername())).toList();
        if (sameUserName.isEmpty()) {
            return usersRepository.save(newUser);
        } else {
            throw new IllegalStateException("Username " + newUser.getUsername() + " already exists");
        }
    }

    public void deleteUser(Long userId) {
        boolean exists = usersRepository.existsById(userId);
        if (!exists) {
            throw new IllegalStateException("User with id " + userId + " does not exist");
        } else {
            usersRepository.deleteById(userId);
        }
    }

    @Transactional
    public void updateUser(Long userId, Users usersDetails) {

        Users updatedUser =
                usersRepository.findById(userId).orElseThrow(() -> new IllegalStateException("User with " + "UserId " + userId + " does not exist"));

        String oldUsername = updatedUser.getUsername();
        String oldPassword = updatedUser.getPassword();

        String newUsername = usersDetails.getUsername();
        String newPassword = usersDetails.getPassword();


        if (newUsername != null) {
            if (newUsername.length() > 3 && !newUsername.equals(oldUsername)) {
                updatedUser.setUsername(newUsername);
            } else {
                throw new IllegalArgumentException("Could not change Username " + oldUsername + " into " + newUsername);
            }
        }

        if (newPassword != null) {
            if (newPassword.length() > 8 && !newPassword.equals(oldPassword)) {
                updatedUser.setPassword(newPassword);
            } else {
                throw new IllegalArgumentException("Could not Change Password");
            }
        }
    }
}