package com.example.sendmessageservice.controller.dto

data class UserDto(
    var idUser : Long? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var username: String? = null,
    var mail: String? = null,
    var password: String? = null,
    var number: String? = null
)