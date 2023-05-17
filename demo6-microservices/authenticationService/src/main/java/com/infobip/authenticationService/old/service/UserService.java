//package com.infobip.authenticationService.old.service;
//
//import com.infobip.authenticationService.old.model.User;
//
//import java.util.List;
//
//public interface UserService {
//    default User saveUser(User userDto) {
//        int id = (int) Math.random();
//        return saveUser(userDto);
//    }
//    List<User> getAllUsers();
//    User getUserById(int id);
//    void deleteUser(int id);
//    User updateUser(User user);
//    User findByUsername(String username);
//}
//
