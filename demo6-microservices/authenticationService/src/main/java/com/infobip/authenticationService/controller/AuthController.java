//package com.infobip.authenticationService.controller;
//
//import com.infobip.authenticationService.dto.AuthRequest;
//import com.infobip.authenticationService.entity.UserCredential;
//import com.infobip.authenticationService.service.AuthService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//public class AuthController {
//
//    @Autowired
//    private AuthService service;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @PostMapping("/v1/register")
//    public String addNewUser(@RequestBody UserCredential user) {
//        return service.saveUser(user);
//    }
//
//    @PostMapping("/v1/token")
//    public String getToken(@RequestBody AuthRequest authRequest) {
//        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
//        if (authenticate.isAuthenticated()) {
//            return service.generateToken(authRequest.getUsername());
//        } else {
//            throw new RuntimeException("invalid access");
//        }
//    }
//
//    @GetMapping("/v1/validate")
//    public String validateToken(@RequestParam("token") String token) {
//        service.validateToken(token);
//        return "Token is valid";
//    }
//}
