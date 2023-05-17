//package com.infobip.userManagementService.controller;
//
//import com.infobip.userManagementService.config.JwtTokenUtil;
//import com.infobip.userManagementService.controller.dto.JwtRequest;
//import com.infobip.userManagementService.controller.dto.JwtResponse;
//import com.infobip.userManagementService.service.JwtUserDetailsService;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.DisabledException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@CrossOrigin
//public class JwtAuthenticationController {
//    @Autowired
//    private AuthenticationManager authenticationManager;
//    @Autowired
//    private JwtTokenUtil jwtTokenUtil;
//    @Autowired
//    private JwtUserDetailsService userDetailsService;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
////    @GetMapping({ "/hello" })
////    public String hello() {
////        return "Hello World";
////    }
//
//    @PostMapping("v1/authenticate")
//    public ResponseEntity<?> createAuthenticationToken(@RequestBody @Valid JwtRequest authenticationRequest) throws Exception{
//        //authenticate the user based on the provided credentials
//        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
//
//        //generate JWT Token
//        final UserDetails userDetails = userDetailsService
//                .loadUserByUsername(authenticationRequest.getUsername());
//        final String token = jwtTokenUtil.generateToken(userDetails);
//        //return JWT Token in response
//        return ResponseEntity.ok(new JwtResponse(token));
//    }
//
//    private void authenticate(String username, String password) throws Exception{
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
//        }catch (DisabledException e){
//            throw new Exception("USER_DISABLED", e);
//        }catch (BadCredentialsException e){
//            throw new Exception("INVALID_CREDENTIALS", e);
//        }
//
//        if(username.isEmpty()){
//
//        }
//    }
//}
