package com.infobip.demo4.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
@Component
@Data
@Builder
@Getter
@Setter
public class MessageDto {

//    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private int idMessage;
    @NotNull(message = "validation.userIdMandatory")
    private int userId;
    @NotBlank(message = "validation.numberMsgMandatory")
    @NotNull(message = "validation.numberMsgMandatory")
    private String number;
    @NotBlank(message = "validation.dateTimeMandatory")
    @NotNull(message = "validation.dateTimeMandatory")
    private String dateTime;
    @NotBlank(message = "validation.messageTextMandatory")
    @NotNull(message = "validation.messageTextMandatory")
    private String messageText;

    public MessageDto() {
    }

    public MessageDto(int idMessage, int userId, String number, String dateTime, String messageText) {
        this.idMessage = idMessage;
        this.userId = userId;
        this.number = number;
        this.dateTime = dateTime;
        this.messageText = messageText;
    }

    //    public Date getSubmissionDateConverted(String timezone) throws ParseException{
//        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
//        return dateFormat.parse(this.dateTime);
//    }
//
//    public void setSubmissionDate(Date date, String timezone){
//        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
//        this.dateTime = dateFormat.format(date);
//    }


}
