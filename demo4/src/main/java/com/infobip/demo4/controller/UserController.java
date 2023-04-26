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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
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
        apiResponse = new ApiResponse();
        userService.saveUser(userDto);
        apiResponse.setCode("8");
        apiResponse.setMessage("User with username " + userDto.getUsername() + " successfully created.");
        apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/users/v1/8");
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int id){
        User user = userService.getUserById(id);
        if(user != null) {
            return ResponseEntity.ok(user);
        } else{
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity <ApiResponse> updateUser(@PathVariable("id") int id, @RequestBody @Valid UserDto userDto) {
        apiResponse = new ApiResponse();
        userDto.setIdUser(id);
        userService.updateUser(userDto);
        apiResponse.setCode("9");
        apiResponse.setMessage("User with username " + userDto.getUsername() + " successfully updated.");
        apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/users/v1/9");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        apiResponse.setTimestamp(df.format(new Date()));
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id) {
        return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody UserDto userDto) {
        apiResponse = new ApiResponse();
        userService.saveUser(userDto);
        apiResponse.setCode("10");
        apiResponse.setMessage("User with username " + userDto.getUsername() + " successfully registered.");
        apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/users/v1/10");
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}
