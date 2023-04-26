package com.infobip.demo4.controller.dto;

import com.infobip.model.SmsResponseDetails;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApiResponse {
    private String code;
    private String message;

    private String docsURL;

    private List<SmsResponseDetails> smsResponseDetails;

    private String timestamp;

    public ApiResponse() {

    }

    public ApiResponse(String code, String message, String docsURL, List<SmsResponseDetails> smsResponseDetails, String timestamp) {
        this.code = code;
        this.message = message;
        this.docsURL = docsURL;
        this.smsResponseDetails = smsResponseDetails;
        this.timestamp = timestamp;
    }

}
