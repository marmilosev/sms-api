package com.example.mydemoproject.userservice.repository;

import com.example.mydemoproject.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

//    @Query("SELECT u FROM users u WHERE u.email = ?1")
//    User findByEmail(String email);
}
