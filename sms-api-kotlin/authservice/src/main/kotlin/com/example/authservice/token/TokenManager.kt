package com.example.authservice.token

import com.example.authservice.dto.UserDto


interface TokenManager {
    fun generateToken(userDto: UserDto?): String?
    fun validateToken(token: String?)
}
