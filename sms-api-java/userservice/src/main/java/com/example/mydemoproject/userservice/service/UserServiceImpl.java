package com.example.mydemoproject.userservice.service;

import com.example.mydemoproject.userservice.dto.UserDto;
import com.example.mydemoproject.userservice.model.User;
import com.example.mydemoproject.userservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

//    @Override
//    public UserDto findUserByEmail(String email) {
//        User user = userRepository.findByEmail(email);
//
//        return new UserDto(
//                user.getId(),
//                user.getFirstName(),
//                user.getLastName(),
//                user.getEmail()
//        );
//    }

    @Override
    public UserDto getUser(Long userId) {
        User user = userRepository.findById(userId).get();

        return new UserDto(
                userId,
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }
}
