package com.infobip.sendMessageService.controller.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@Builder
@Getter
@Setter
public class ResponseDto {

    private UserDto user;
    private MessageDto message;

    public ResponseDto() {
    }

    public ResponseDto(UserDto user, MessageDto message) {
        this.user = user;
        this.message = message;
    }
}
