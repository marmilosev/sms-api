package com.infobip.demo3.service;

import com.infobip.demo3.model.User;

import java.util.List;

public interface UserService {
    default User saveUser(User user) {
        int id = (int) Math.random();
        return saveUser(user);
    }
    List<User> getAllUsers();
    User getUserById(int id);
    String deleteUser(int id);
    User updateUser(User user);

    User findByEmail(String email);
    User findByNumber(String number);
    User findByUsername(String username);
}

