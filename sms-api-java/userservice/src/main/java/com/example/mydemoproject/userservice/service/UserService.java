package com.example.mydemoproject.userservice.service;

import com.example.mydemoproject.userservice.dto.UserDto;
import com.example.mydemoproject.userservice.model.User;

public interface UserService {
    User saveUser(User user);
    UserDto getUser(Long userId);

//    UserDto findUserByEmail(String email);
}
