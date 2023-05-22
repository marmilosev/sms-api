package com.infobip.sendMessageService.controller;

import com.infobip.sendMessageService.controller.dto.ApiResponse;
import com.infobip.sendMessageService.controller.dto.UserDto;
import com.infobip.sendMessageService.model.User;
import com.infobip.sendMessageService.service.UserService;
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
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto userDto) {
        User userRequest = modelMapper.map(userDto, User.class);
        User user = userService.saveUser(userRequest);
        UserDto userResponse = modelMapper.map(user, UserDto.class);
        return new ResponseEntity<UserDto>(userResponse, HttpStatus.CREATED);
//        apiResponse = new ApiResponse();
//        userService.saveUser(userDto);
//        apiResponse.setCode("8");
//        apiResponse.setMessage("User with username " + userDto.getUsername() + " successfully created.");
//        apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/users/v1/8");
//        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
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
//        if(user != null) {
//            return ResponseEntity.ok(user);
//        } else{
//            return ResponseEntity.notFound().build();
//        }
    }
    @PutMapping("/{id}")
    public ResponseEntity <UserDto> updateUser(@PathVariable("id") int id, @RequestBody @Valid UserDto userDto) {
        userDto.setIdUser(id);
        User userRequest = modelMapper.map(userDto, User.class);
        User user = userService.updateUser(userRequest);
        UserDto userResponse = modelMapper.map(user, UserDto.class);
        return ResponseEntity.ok().body(userResponse);
//        apiResponse = new ApiResponse();
//        userDto.setIdUser(id);
//        userService.updateUser(userDto);
//        apiResponse.setCode("9");
//        apiResponse.setMessage("User with username " + userDto.getUsername() + " successfully updated.");
//        apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/users/v1/9");
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        df.setTimeZone(TimeZone.getTimeZone("UTC"));
//        apiResponse.setTimestamp((Timestamp) new Date());
//        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        apiResponse = new ApiResponse();
        apiResponse.setMessage("Deleted");
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
//        return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
    }


}
