package com.example.mydemoproject.authservice.controller;

import com.example.mydemoproject.authservice.controller.dto.UserDtoRegister;
import com.example.mydemoproject.authservice.dto.ApiResponse;
import com.example.mydemoproject.authservice.dto.UserDto;
import com.example.mydemoproject.authservice.model.User;
import com.example.mydemoproject.authservice.service.UserService;
import com.example.mydemoproject.authservice.token.TokenManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
@Slf4j
public class AuthenticationController {
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;
    ApiResponse apiResponse;
    final static Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    TokenManager tokenManager;

    @PostMapping("login")
    public ResponseEntity<String> authenticate(@RequestBody UserDto userDto) {
        logger.info("Received validate request.");
        return new ResponseEntity<>(tokenManager.generateToken(userDto), HttpStatus.OK);
    }

    @PostMapping("/validate")
    public void validate(@RequestBody String token) {
        logger.info("Received validate request.");
        tokenManager.validateToken(token);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> createUser(@RequestBody UserDtoRegister userDto) {
        User userRequest = modelMapper.map(userDto, User.class);
        User user = userService.saveUser(userRequest);
        UserDto userResponse = modelMapper.map(user, UserDto.class);
        apiResponse = new ApiResponse();
        apiResponse.setCode("8");
        apiResponse.setMessage("User with username " + userDto.getUsername() + " successfully created.");
        apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/users/v1/8");
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}


