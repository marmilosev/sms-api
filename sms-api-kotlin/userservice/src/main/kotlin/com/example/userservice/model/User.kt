package com.example.userservice.model

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