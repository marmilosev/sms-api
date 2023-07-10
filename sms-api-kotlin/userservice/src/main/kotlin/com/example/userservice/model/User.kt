package com.example.userservice.model

import jakarta.persistence.*

@Entity
@Table(name="users")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var idUser: Long = 0
    private var firstName: String? = null
    private var lastName: String? = null
    private var username: String? = null
    private var mail: String? = null
    private var password: String? = null
    private var number: String? = null
}