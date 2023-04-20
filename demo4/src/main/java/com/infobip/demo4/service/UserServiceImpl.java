package com.infobip.demo4.service;

import com.infobip.demo4.model.User;
import com.infobip.demo4.repository.UserRepository;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        User savedUser = userRepository.save(user);
        user.setIdUser(savedUser.getIdUser());
        user.setFirstName(savedUser.getFirstName());
        user.setLastName(savedUser.getLastName());
        user.setUsername(savedUser.getUsername());
        user.setMail(savedUser.getMail());

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2i);
        //iteration, memory, parallelism
        String hashPassword = argon2.hash(2, 1024, 4, savedUser.getPassword());
        user.setPassword(hashPassword);
        user.setPassword(savedUser.getPassword());
//        user.setNumber(savedUser.getNumber());

        userRepository.save(user);

        return user;
    }


    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).get();
    }

    @Override
    public String deleteUser(int id) {
        userRepository.deleteById(id);
        return "User with id " + id + " has been deleted.";
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
    public User findByEmail (String email){
        return userRepository.findByUsername(email);
    }

    @Override
    public User findByNumber(String number) {
        return userRepository.findByNumber(number);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


}
