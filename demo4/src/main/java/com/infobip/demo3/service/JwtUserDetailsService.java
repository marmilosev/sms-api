package com.infobip.demo3.service;

import com.infobip.demo3.controller.MyUserPrinciple;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class JwtUserDetailsService implements UserDetailsService {
    //private JwtRequest jwtRequest;
    private final UserServiceImpl userService;

    public JwtUserDetailsService(UserServiceImpl userService) {
        this.userService = userService;
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        com.infobip.demo3.model.User user = userService.findByUsername(username);
//        if(user == null){
//            throw new UsernameNotFoundException("User with username: " + username + " not found.");
//        }
//        return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.infobip.demo3.model.User myUser = userService.findByUsername(username);
        return new MyUserPrinciple(myUser);

//        if ("marija".equals(username)) {
//            return new User("marija", "$argon2i$v=19$m=16,t=2,p=1$TmpkOGtQQW5wSGVoc2FXWQ$tcwSk7xFXEfcgk+xu+Mgxw",
//                    new ArrayList<>());
//        } else {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }
    }
}
