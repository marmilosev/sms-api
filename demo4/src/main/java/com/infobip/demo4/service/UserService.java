package com.infobip.demo4.service;

import com.infobip.demo4.controller.dto.UserDto;
import com.infobip.demo4.model.User;

import java.util.List;

public interface UserService {
    default User saveUser(UserDto user) {
        int id = (int) Math.random();
        return saveUser(user);
    }
    List<User> getAllUsers();
    User getUserById(int id);
    String deleteUser(int id);
    User updateUser(UserDto user);
    User findByUsername(String username);
}

