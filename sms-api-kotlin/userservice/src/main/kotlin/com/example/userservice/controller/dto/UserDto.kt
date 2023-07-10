package com.example.userservice.controller.dto

data class UserDto(
    var idUser : Long,
    var firstName: String,
    var lastName: String,
    var username: String,
    var mail: String,
    var password: String,
    var number: String
)