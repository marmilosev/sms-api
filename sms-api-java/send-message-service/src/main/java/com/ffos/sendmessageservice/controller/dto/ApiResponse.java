package com.ffos.sendmessageservice.controller.dto;

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

//    private List<SmsResponseDetails> smsResponseDetails;

    public ApiResponse() {

    }

    public ApiResponse(String code, String message, String docsURL) {
        this.code = code;
        this.message = message;
        this.docsURL = docsURL;
//        this.smsResponseDetails = smsResponseDetails;
    }

}

