package com.example.mydemoproject.userservice.controller;

import com.example.mydemoproject.userservice.controller.dto.ApiResponse;
import com.example.mydemoproject.userservice.controller.dto.UserDto;
import com.example.mydemoproject.userservice.model.User;
import com.example.mydemoproject.userservice.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/v1/users")
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;
    ApiResponse apiResponse;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createUser(@RequestBody @Valid UserDto userDto) {
        User userRequest = modelMapper.map(userDto, User.class);
        User user = userService.saveUser(userRequest);
        UserDto userResponse = modelMapper.map(user, UserDto.class);
        apiResponse = new ApiResponse();
        apiResponse.setCode("8");
        apiResponse.setMessage("User with username " + userDto.getUsername() + " successfully created.");
        apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/users/v1/8");
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
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
    public ResponseEntity <ApiResponse> updateUser(@PathVariable("id") int id, @RequestBody @Valid UserDto userDto) {
        userDto.setIdUser(id);
        User userRequest = modelMapper.map(userDto, User.class);
        User user = userService.updateUser(userRequest);
//        return ResponseEntity.ok().body(userResponse);
        apiResponse = new ApiResponse();
        userDto.setIdUser(id);
        userService.updateUser(user);
        UserDto userResponse = modelMapper.map(user, UserDto.class);
        apiResponse.setCode("9");
        apiResponse.setMessage("User with username " + userDto.getUsername() + " successfully updated.");
        apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/users/v1/9");
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        apiResponse = new ApiResponse();
        apiResponse.setCode("11");
        apiResponse.setMessage("User successfully deleted.");
        apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/users/v1/11");
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}
