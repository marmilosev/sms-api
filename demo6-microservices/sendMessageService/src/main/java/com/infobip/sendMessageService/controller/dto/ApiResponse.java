package com.infobip.sendMessageService.controller.dto;

import com.infobip.model.SmsResponseDetails;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class ApiResponse {
    private String code;
    private String message;

    private String docsURL;

    private List<SmsResponseDetails> smsResponseDetails;

    private Timestamp timestamp;

    public ApiResponse() {

    }

    public ApiResponse(String code, String message, String docsURL, List<SmsResponseDetails> smsResponseDetails) {
        this.code = code;
        this.message = message;
        this.docsURL = docsURL;
        this.smsResponseDetails = smsResponseDetails;
        //this.timestamp = timestamp;
    }

}

