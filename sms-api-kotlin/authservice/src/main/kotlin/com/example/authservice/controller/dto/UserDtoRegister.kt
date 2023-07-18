package com.example.authservice.controller.dto

data class UserDtoRegister(
    var idUser : Long,
    var firstName: String,
    var lastName: String,
    var username: String,
    var mail: String,
    var password: String,
    var number: String
)