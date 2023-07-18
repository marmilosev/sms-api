package com.example.mydemoproject.authservice.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Data
@Builder
@Getter
@Setter
public class UserDtoRegister {
    private long idUser;
    private String firstName;
    private String lastName;
    private String username;
    private String mail;
    private String password;
    private String number;

    public UserDtoRegister() {
    }

    public UserDtoRegister(long idUser, String firstName, String lastName, String username, String mail, String password, String number) {
        this.idUser = idUser;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.number = number;
    }
}