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


    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto) {
        User userRequest = modelMapper.map(userDto, User.class);
        User user = userService.saveUser(userRequest);
        UserDto userResponse = modelMapper.map(user, UserDto.class);
        return new ResponseEntity<UserDto>(userResponse, HttpStatus.CREATED);
    }

}
