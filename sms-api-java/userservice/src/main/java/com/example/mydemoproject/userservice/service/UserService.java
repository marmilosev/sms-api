package com.example.mydemoproject.userservice.service;

import com.example.mydemoproject.userservice.model.User;

import java.util.List;

public interface UserService {

    default User saveUser(User user) {
        long id = (long) Math.random();
        return saveUser(user);
    }
    List<User> getAllUsers();
    User getUserById(long id);
    void deleteUser(long id);
    User updateUser(User user);
    User findByUsername(String username);
}
