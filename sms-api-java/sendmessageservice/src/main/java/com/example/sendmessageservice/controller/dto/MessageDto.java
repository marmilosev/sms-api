package com.example.sendmessageservice.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
@Builder
@Getter
@Setter
public class MessageDto {

//    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private int idMessage;
    @NotNull(message = "validation.userMandatory")
    private UserDto user;
    @NotBlank(message = "validation.numberMsgMandatory")
    @NotNull(message = "validation.numberMsgMandatory")
    private String number;
//    @NotBlank(message = "validation.dateTimeMandatory")
    @NotNull(message = "validation.dateTimeMandatory")
    private Date dateTime;
    @NotBlank(message = "validation.messageTextMandatory")
    @NotNull(message = "validation.messageTextMandatory")
    private String messageText;

    public MessageDto() {
    }

    public MessageDto(int idMessage, UserDto user, String number, Date dateTime, String messageText) {
        this.idMessage = idMessage;
        this.user = user;
        this.number = number;
        this.dateTime = dateTime;
        this.messageText = messageText;
    }

}
