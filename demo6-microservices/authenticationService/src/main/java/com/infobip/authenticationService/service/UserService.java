package com.infobip.authenticationService.service;

import com.infobip.authenticationService.model.User;

import java.util.List;

public interface UserService {
    default User saveUser(User user){
        int id = (int) Math.random();
        return saveUser(user);
    }
    List<User> getAllUsers();
    User getUserById(int id);
    void deleteUser(int id);
    User updateUser(User user);
    User findByUsername(String username);
}

