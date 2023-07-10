package com.example.userservice.service

import com.example.userservice.model.User


interface UserService {
    fun saveUser(user: User): User {
        val id = Math.random().toLong()
        return saveUser(user)
    }

    val getAllUsers: List<User>

    fun getUserById(id: Long): User
    fun deleteUser(id: Long)
    fun updateUser(id: Long, user: User): User
    fun findByUsername(username: String): User
}


