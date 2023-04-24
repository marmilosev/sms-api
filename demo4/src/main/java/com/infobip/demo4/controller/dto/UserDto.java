package com.infobip.demo4.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class UserDto {
    private int idUser;
    @NotBlank(message = "First name is mandatory")
    @NotNull(message = "First name is mandatory")
    private String firstName;
    @NotBlank(message = "Last name is mandatory")
    @NotNull(message = "Last name is mandatory")
    private String lastName;
    @NotBlank(message = "Username is mandatory")
    @NotNull(message = "Username is mandatory")
    private String username;
    @NotBlank(message = "Mail is mandatory")
    @NotNull(message = "Mail is mandatory")
    @Email(message = "Invalid email")
    private String mail;
    @NotBlank(message = "Password is mandatory")
    @NotNull(message = "Password is mandatory")
    private String password;
    @NotBlank(message = "Number is mandatory")
    @NotNull(message = "Number is mandatory")
    private String number;
}
