package com.example.sendmessageservice.controller.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AuthDto {

    private String token;

    public AuthDto() {
    }

    public AuthDto(String token) {
        this.token = token;
    }
}
