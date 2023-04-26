package com.infobip.demo4.service;

import com.infobip.demo4.controller.dto.UserDto;
import com.infobip.demo4.model.User;

import java.util.List;

public interface UserService {
    default User saveUser(UserDto userDto) {
        int id = (int) Math.random();
        return saveUser(userDto);
    }
    List<User> getAllUsers();
    User getUserById(int id);
    String deleteUser(int id);
    User updateUser(UserDto user);
    User findByUsername(String username);
}

