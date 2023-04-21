package com.infobip.demo4.controller;

import com.infobip.demo4.controller.dto.ApiResponse;
import com.infobip.demo4.controller.dto.UserDto;
import com.infobip.demo4.model.User;
import com.infobip.demo4.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequestMapping("/v1/users")
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;
    private ApiResponse apiResponse;

    @PostMapping("/add")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        User userRequest = modelMapper.map(userDto, User.class);
        User user = userService.saveUser(userRequest);
        UserDto userResponse = modelMapper.map(user, UserDto.class);
        return new ResponseEntity<UserDto>(userResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public List<UserDto> getAllUsers(){
        return userService.getAllUsers().stream().map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
}

        @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") int id){
            User user = userService.getUserById(id);
            UserDto userResponse = modelMapper.map(user, UserDto.class);
            return ResponseEntity.ok().body(userResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity <UserDto> updateUser(@Valid @PathVariable int id, @RequestBody UserDto userDto) {
        User userRequest = modelMapper.map(userDto, User.class);
        userRequest.setIdUser(id);
        User user = userService.updateUser(userRequest);
        UserDto userResponse = modelMapper.map(user, UserDto.class);
        return ResponseEntity.ok().body(userResponse);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }


//    @PostMapping("/register")
//    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody User user, Errors errors){
//        apiResponse = new ApiResponse();
//        if(errors.hasErrors()){
//            if(user.getFirstName().isBlank()) {
//                apiResponse.setCode(1);
//                apiResponse.setMessage("Error with first name input");
//                apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/users/v1/1");
//            }
//            else if(user.getLastName().isBlank()) {
//                apiResponse.setCode(2);
//                apiResponse.setMessage("Error with last name input");
//                apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/users/v1/2");
//            }
//            else if(user.getUsername().isBlank()) {
//                apiResponse.setCode(3);
//                apiResponse.setMessage("Error with username input");
//                apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/users/v1/3");
//            }
//            else if(user.getNumber().isBlank()) {
//                apiResponse.setCode(4);
//                apiResponse.setMessage("Error with number input");
//                apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/users/v1/4");
//            }
//            else if(user.getMail().isBlank()) {
//                apiResponse.setCode(5);
//                apiResponse.setMessage("Error with mail input");
//                apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/users/v1/5");
//            }
//            else if(user.getPassword().isBlank()) {
//                apiResponse.setCode(6);
//                apiResponse.setMessage("Error with password input");
//                apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/users/v1/6");
//            }
//
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
//        }
//
//        // all ok - save User in database
//        user=userService.saveUser(user);
//
//        apiResponse.setCode(7);
//        apiResponse.setMessage("User with username " + user.getUsername() + " successfully registered.");
//        apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/users/v1/7");
//        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
//    }

}
