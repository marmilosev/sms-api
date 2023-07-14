package com.example.mydemoproject.authservice.token;

import com.example.mydemoproject.authservice.dto.UserDto;

public interface TokenManager {
    String generateToken(UserDto userDto);
    void validateToken(String token);
}
