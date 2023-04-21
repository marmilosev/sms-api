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

@RequestMapping("/v1/users")
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;
    private ApiResponse apiResponse;

    @PostMapping("/add")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDto userDto) {
        Map<String, Object> jsonResponseMap = new LinkedHashMap<String, Object>();
        User user = modelMapper.map(userDto, User.class);
        userService.saveUser(user);
        jsonResponseMap.put("status", 1);
        jsonResponseMap.put("message", "User saved successfully");
        return new ResponseEntity<>(jsonResponseMap, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        Map<String, Object> jsonResponseMap = new LinkedHashMap<String, Object>();
        List<User> listOfUser = userService.getAllUsers();
        List<UserDto> listOfUserDto = new ArrayList<UserDto>();
        if(!listOfUserDto.isEmpty()){
            for (User user: listOfUser ) {
                listOfUserDto.add(modelMapper.map(user, UserDto.class));
            }
            jsonResponseMap.put("status", 1);
            jsonResponseMap.put("data", listOfUserDto);
            return new ResponseEntity < > (jsonResponseMap, HttpStatus.OK);
        } else {
            jsonResponseMap.clear();
            jsonResponseMap.put("status", 0);
            jsonResponseMap.put("message", "Data is not found");
            return new ResponseEntity < > (jsonResponseMap, HttpStatus.NOT_FOUND);
        }
}

        @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") int id){
            Map <String, Object > jsonResponseMap = new LinkedHashMap<String, Object>();
            try {
                User user = userService.getUserById(id);
                UserDto userDto = modelMapper.map(user, UserDto.class);
                jsonResponseMap.put("status", 1);
                jsonResponseMap.put("data" , userDto);
                return new ResponseEntity<>(jsonResponseMap, HttpStatus.OK);
            }catch (Exception e){
                jsonResponseMap.clear();
                jsonResponseMap.put("status", 0);
                jsonResponseMap.put("message", "User not found");
                return new ResponseEntity<>(jsonResponseMap, HttpStatus.NOT_FOUND);
            }
    }

    @PutMapping("/{id}")
    public ResponseEntity < ? > updateUser(@Valid @PathVariable int id, @RequestBody UserDto userDto) {
        Map < String, Object > jsonResponseMap = new LinkedHashMap < String, Object > ();
        try {
            User user = userService.getUserById(id);
            user.setFirstName(userDto.getFirstname());
            user.setLastName(user.getLastName());
            user.setMail(userDto.getMail());
            user.setNumber(userDto.getNumber());
            user.setUsername(userDto.getUsername());
            user.setPassword(userDto.getPassword());
            userService.updateUser(user);
            jsonResponseMap.put("status", 1);
            jsonResponseMap.put("data", userService.getUserById(id));
            return new ResponseEntity < > (jsonResponseMap, HttpStatus.OK);
        } catch (Exception ex) {
            jsonResponseMap.clear();
            jsonResponseMap.put("status", 0);
            jsonResponseMap.put("message", "Data is not found");
            return new ResponseEntity < > (jsonResponseMap, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity < ? > deleteUser(@PathVariable int id) {
        Map < String, Object > jsonResponseMap = new LinkedHashMap < String, Object > ();
        try {
            User user = userService.getUserById(id);
            userService.deleteUser(id);
            jsonResponseMap.put("status", 1);
            jsonResponseMap.put("message", "Record is deleted successfully!");
            return new ResponseEntity < > (jsonResponseMap, HttpStatus.OK);
        } catch (Exception ex) {
            jsonResponseMap.clear();
            jsonResponseMap.put("status", 0);
            jsonResponseMap.put("message", "Data is not found");
            return new ResponseEntity < > (jsonResponseMap, HttpStatus.NOT_FOUND);
        }
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
