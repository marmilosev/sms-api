//package com.infobip.authenticationService.old.controller;//package com.infobip.userManagementService.controller;
//
//import com.infobip.authenticationService.old.config.JwtTokenUtil;
//import com.infobip.authenticationService.old.controller.dto.JwtRequest;
//import com.infobip.authenticationService.old.controller.dto.JwtResponse;
//import com.infobip.authenticationService.old.controller.dto.UserDto;
//import com.infobip.authenticationService.old.model.User;
//import com.infobip.authenticationService.old.service.JwtUserDetailsService;
//import com.infobip.authenticationService.old.service.UserService;
//import jakarta.validation.Valid;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
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
//    @Autowired
//    private ModelMapper modelMapper;
//    @Autowired
//    private UserService userService;
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
//    @PostMapping("v1/register")
//    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto) {
//        User userRequest = modelMapper.map(userDto, User.class);
//        User user = userService.saveUser(userRequest);
//        UserDto userResponse = modelMapper.map(user, UserDto.class);
//        return new ResponseEntity<UserDto>(userResponse, HttpStatus.CREATED);
////        apiResponse = new ApiResponse();
////        userService.saveUser(userDto);
////        apiResponse.setCode("10");
////        apiResponse.setMessage("User with username " + userDto.getUsername() + " successfully registered.");
////        apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/users/v1/10");
////        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
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
