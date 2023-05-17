package com.infobip.sendMessageService.controller.dto;


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
public class AuthDto {

    private String token;

    public AuthDto() {
    }

    public AuthDto(String token) {
        this.token = token;
    }
}