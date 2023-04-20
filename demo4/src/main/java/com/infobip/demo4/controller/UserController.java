package com.infobip.demo4.controller;

import com.infobip.demo4.controller.dto.ApiResponse;
import com.infobip.demo4.model.User;
import com.infobip.demo4.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/users")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    private ApiResponse apiResponse;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createUser(@Valid @RequestBody User user, Errors errors){
        apiResponse = new ApiResponse();
        if(errors.hasErrors()){
            if(user.getFirstName().isBlank()) {
                apiResponse.setCode(1);
                apiResponse.setMessage("Error with first name input");
                apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/users/v1/1");
            }
            else if(user.getLastName().isBlank()) {
                apiResponse.setCode(2);
                apiResponse.setMessage("Error with last name input");
                apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/users/v1/2");
            }
            else if(user.getUsername().isBlank()) {
                apiResponse.setCode(3);
                apiResponse.setMessage("Error with username input");
                apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/users/v1/3");
            }
            else if(user.getNumber().isBlank()) {
                apiResponse.setCode(4);
                apiResponse.setMessage("Error with number input");
                apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/users/v1/4");
            }
            else if(user.getMail().isBlank()) {
                apiResponse.setCode(5);
                apiResponse.setMessage("Error with mail input");
                apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/users/v1/5");
            }
            else if(user.getPassword().isBlank()) {
                apiResponse.setCode(6);
                apiResponse.setMessage("Error with password input");
                apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/users/v1/6");
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }

        // all ok - save User in database
        user=userService.saveUser(user);

        apiResponse.setCode(7);
        apiResponse.setMessage("User with username " + user.getUsername() + " successfully created.");
        apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/users/v1/7");
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
    public ResponseEntity<ApiResponse> updateUser(@Valid @PathVariable("id") int id, @RequestBody User user, Errors errors){
        apiResponse = new ApiResponse();

        user.setIdUser(id);
        try {
            userService.updateUser(user);
            apiResponse.setCode(8);
            apiResponse.setMessage("User with username " + user.getUsername() + " has been successfully updated.");
            apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/users/v1/8");
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }catch (Exception e){
            apiResponse.setCode(9);
            apiResponse.setMessage("Error updating customer");
            apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/users/v1/9");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }

    }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id){
        return userService.deleteUser(id);
    }



    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody User user, Errors errors){
        apiResponse = new ApiResponse();
        if(errors.hasErrors()){
            if(user.getFirstName().isBlank()) {
                apiResponse.setCode(1);
                apiResponse.setMessage("Error with first name input");
                apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/users/v1/1");
            }
            else if(user.getLastName().isBlank()) {
                apiResponse.setCode(2);
                apiResponse.setMessage("Error with last name input");
                apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/users/v1/2");
            }
            else if(user.getUsername().isBlank()) {
                apiResponse.setCode(3);
                apiResponse.setMessage("Error with username input");
                apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/users/v1/3");
            }
            else if(user.getNumber().isBlank()) {
                apiResponse.setCode(4);
                apiResponse.setMessage("Error with number input");
                apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/users/v1/4");
            }
            else if(user.getMail().isBlank()) {
                apiResponse.setCode(5);
                apiResponse.setMessage("Error with mail input");
                apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/users/v1/5");
            }
            else if(user.getPassword().isBlank()) {
                apiResponse.setCode(6);
                apiResponse.setMessage("Error with password input");
                apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/users/v1/6");
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }

        // all ok - save User in database
        user=userService.saveUser(user);

        apiResponse.setCode(7);
        apiResponse.setMessage("User with username " + user.getUsername() + " successfully registered.");
        apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/users/v1/7");
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}
