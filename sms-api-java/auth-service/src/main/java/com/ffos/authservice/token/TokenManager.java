package com.ffos.authservice.token;

import com.ffos.authservice.controller.dto.UserDto;

public interface TokenManager {
    String generateToken(UserDto userDto);
    void validateToken(String token);
}
