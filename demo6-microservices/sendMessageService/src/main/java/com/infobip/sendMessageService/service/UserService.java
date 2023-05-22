package com.infobip.sendMessageService.service;

import com.infobip.sendMessageService.model.User;

import java.util.List;

public interface UserService {
    default User saveUser(User userDto) {
        int id = (int) Math.random();
        return saveUser(userDto);
    }
    List<User> getAllUsers();
    User getUserById(int id);
    void deleteUser(int id);
    User updateUser(User user);
    User findByUsername(String username);
}

