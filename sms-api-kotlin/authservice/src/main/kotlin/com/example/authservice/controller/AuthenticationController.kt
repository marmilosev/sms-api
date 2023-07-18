package com.example.authservice.controller

import com.example.authservice.dto.ApiResponse
import com.example.authservice.dto.UserDto
import com.example.authservice.service.UserServiceImpl
import com.example.authservice.token.TokenManager
import com.example.userservice.model.User
import com.example.userservice.model.toUserDto
import lombok.AllArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
@Slf4j
class AuthenticationController (val userServiceImpl: UserServiceImpl){

    var apiResponse: ApiResponse? = null
    @Autowired
    var tokenManager: TokenManager? = null
    @PostMapping("login")
    fun authenticate(@RequestBody userDto: UserDto?): ResponseEntity<String?> {
        logger.info("Received validate request.")
        return ResponseEntity<String?>(tokenManager?.generateToken(userDto), HttpStatus.OK)
    }

    @PostMapping("/validate")
    fun validate(@RequestBody token: String?) {
        logger.info("Received validate request.")
        tokenManager?.validateToken(token)
    }

    companion object {
        val logger = LoggerFactory.getLogger(AuthenticationController::class.java)
    }

    @PostMapping("/register")
    fun saveUser(@RequestBody user: User) : ResponseEntity<ApiResponse> {
        var savedUser = userServiceImpl.saveUser(user)
        savedUser.toUserDto()
        apiResponse = ApiResponse(
            "8",
            "User with username ${savedUser.username} successfully created.",
            "https://mmilosevic-diplomski-api.com/users/v1/8"
        )
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse)
    }
}
