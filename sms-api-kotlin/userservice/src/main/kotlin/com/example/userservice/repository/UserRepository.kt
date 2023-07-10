package com.example.userservice.repository

import com.example.userservice.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long> {


    @Query("select u from User u where u.username=:username")
    fun findByUsername(@Param("username") username: String): User

}