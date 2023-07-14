package com.example.authservice.token

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.example.authservice.dto.UserDto
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*


@Component
class TokenManagerImpl : TokenManager {
    @Value("\${jwt.secret}")
    private val secret: String? = null
    private var algorithm: Algorithm? = null
    @PostConstruct
    fun init() {
        algorithm = Algorithm.HMAC512(secret!!.toByteArray())
    }
        override fun generateToken(userDto: UserDto?): String? {
            return JWT.create()
                .withSubject(userDto?.email)
                .withClaim("userId", userDto?.userId)
                .withIssuedAt(Date())
                .withIssuer(ISSUER)
                .sign(algorithm)
    }

    override fun validateToken(token: String?) {
        try {
            val verifier = JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build()
            verifier.verify(token)
        } catch (exception: JWTVerificationException) {
            throw RuntimeException("Invalid token!")
        }
    }

    companion object {
        private const val ISSUER = "auth"
    }
}
