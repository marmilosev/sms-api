package com.example.sendmessageservice.model

import com.example.sendmessageservice.controller.dto.UserDto
import jakarta.persistence.*

@Entity
@Table(name = "users")
class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idUser: Long? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var username: String? = null,
    var mail: String? = null,
    var password: String? = null,
    var number: String? = null
)

fun User.toUserDto() = UserDto(
    idUser = idUser,
    firstName = firstName,
    lastName = lastName,
    username = username,
    mail = mail,
    password = password,
    number = number
)