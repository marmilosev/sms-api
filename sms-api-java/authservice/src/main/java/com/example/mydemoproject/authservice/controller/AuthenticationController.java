package com.example.mydemoproject.authservice.controller;

import com.example.mydemoproject.authservice.dto.UserDto;
import com.example.mydemoproject.authservice.token.TokenManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
}


