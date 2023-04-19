package com.infobip.demo3.controller.dto;

import com.infobip.model.SmsResponseDetails;

import java.util.List;

public class ApiResponse {

    private int code;
    private String message;

    private String docsURL;

    private List<SmsResponseDetails> smsResponseDetails;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDocsURL() {
        return docsURL;
    }

    public void setDocsURL(String docsURL) {
        this.docsURL = docsURL;
    }

    public List<SmsResponseDetails> getSmsResponseDetails() {
        return smsResponseDetails;
    }

    public void setSmsResponseDetails(List<SmsResponseDetails> smsResponseDetails) {
        this.smsResponseDetails = smsResponseDetails;
    }
}
