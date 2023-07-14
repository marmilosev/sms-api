package com.example.sendmessageservice.controller.dto;

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
public class UserDto {
    private int idUser;
    @NotBlank(message = "validation.firstNameMandatory")
    @NotNull(message = "validation.firstNameMandatory")
    private String firstName;
    @NotBlank(message = "validation.lastNameMandatory")
    @NotNull(message = "validation.lastNameMandatory")
    private String lastName;
    @NotBlank(message = "validation.userNameMandatory")
    @NotNull(message = "validation.userNameMandatory")
    private String username;
    @NotBlank(message = "validation.mailMandatory")
    @NotNull(message = "validation.mailMandatory")
    @Email(message = "validation.mailMandatoryAtSign")
    private String mail;
    @NotBlank(message = "validation.passwordMandatory")
    @NotNull(message = "validation.passwordMandatory")
    private String password;
    @NotBlank(message = "validation.numberMandatory")
    @NotNull(message = "validation.numberMandatory")
    private String number;

    public UserDto() {
    }

    public UserDto(int idUser, String firstName, String lastName, String username, String mail, String password, String number) {
        this.idUser = idUser;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.number = number;
    }
}