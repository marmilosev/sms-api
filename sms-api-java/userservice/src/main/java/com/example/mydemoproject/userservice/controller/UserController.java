package com.example.mydemoproject.userservice.controller;

import com.example.mydemoproject.userservice.dto.UserDto;
import com.example.mydemoproject.userservice.model.User;
import com.example.mydemoproject.userservice.service.UserService;
import jakarta.ws.rs.QueryParam;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long userId) {
        UserDto userDto = userService.getUser(userId);
        return ResponseEntity.ok(userDto);
    }

//    @GetMapping
//    public ResponseEntity<UserDto> findUserByEmail(@QueryParam("email") String email) {
//        UserDto userDto = userService.findUserByEmail(email);
//        return ResponseEntity.ok(userDto);
//    }
}
