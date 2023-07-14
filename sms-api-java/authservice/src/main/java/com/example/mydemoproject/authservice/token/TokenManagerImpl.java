package com.example.mydemoproject.authservice.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.mydemoproject.authservice.dto.UserDto;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenManagerImpl implements TokenManager {

    private static final String ISSUER = "auth";

    @Value("${jwt.secret}")
    private String secret;

    private Algorithm algorithm;

    @PostConstruct
    public void init() {
        this.algorithm = Algorithm.HMAC512(secret.getBytes());
    }

    @Override
    public String generateToken(UserDto userDto) {
        return JWT.create()
                .withSubject(userDto.getEmail())
                .withClaim("userId", userDto.getUserId())
                .withIssuedAt(new Date())
                .withIssuer(ISSUER)
                .sign(algorithm);
    }

    @Override
    public void validateToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();

            verifier.verify(token);
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Invalid token!");
        }
    }
}
