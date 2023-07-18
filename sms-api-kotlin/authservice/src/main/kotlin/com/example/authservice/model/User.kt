package com.example.userservice.model

import com.example.authservice.controller.dto.UserDtoRegister
import jakarta.persistence.*

@Entity
@Table(name = "users")
class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idUser: Long,
    var firstName: String,
    var lastName: String,
    var username: String,
    var mail: String,
    var password: String,
    var number: String
)

fun User.toUserDto() = UserDtoRegister(
    idUser = idUser,
    firstName = firstName,
    lastName = lastName,
    username = username,
    mail = mail,
    password = password,
    number = number
)