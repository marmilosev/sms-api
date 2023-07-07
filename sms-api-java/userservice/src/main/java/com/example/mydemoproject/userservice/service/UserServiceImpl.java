package com.example.mydemoproject.userservice.service;

import com.example.mydemoproject.userservice.exception.UserNotFoundException;
import com.example.mydemoproject.userservice.model.User;
import com.example.mydemoproject.userservice.repository.UserRepository;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public User saveUser(User user) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2i);
        //iteration, memory, parallelism
        String hashPassword = argon2.hash(2, 1024, 4, user.getPassword());
        user = User.builder()
                .idUser(user.getIdUser())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .mail(user.getMail())
                .number(user.getNumber())
                .password(hashPassword)
                .build();

        return userRepository.save(user);
    }
    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void deleteUser(long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException("User with id " + id + " has not been found.");
        }
    }

    @Override
    public User updateUser(User user) {
        Optional<User> existingUserOptional = userRepository.findById(user.getIdUser());
        if(existingUserOptional.isPresent()) {
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2i);
            String hashPassword = argon2.hash(4, 1024 * 1024, 8, user.getPassword());
            User existingUser = existingUserOptional.get();
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setUsername(user.getUsername());
            existingUser.setMail(user.getMail());
            existingUser.setPassword(hashPassword);
            existingUser.setNumber(user.getNumber());
            userRepository.save(existingUser);

            return existingUser;

        }else{
            throw new IllegalArgumentException("Invalid user ID");
        }
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
