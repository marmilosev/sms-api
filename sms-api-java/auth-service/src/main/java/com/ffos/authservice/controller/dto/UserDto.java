package com.ffos.authservice.controller.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Data
@Getter
@Setter
public class UserDto {
    private long idUser;
    private String username;
    private String password;
    public UserDto() {
    }

}