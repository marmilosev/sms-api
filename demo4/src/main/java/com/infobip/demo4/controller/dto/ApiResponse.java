package com.infobip.demo4.controller.dto;

import com.infobip.model.SmsResponseDetails;
import org.springframework.http.HttpStatus;

import java.util.List;

public class ApiResponse {
    private HttpStatus status;
    private int code;
    private String message;

    private String docsURL;

    private List<SmsResponseDetails> smsResponseDetails;

    public ApiResponse() {
        this.code = code;
        this.message = message;
        this.docsURL = docsURL;
    }
    public void setStatus(HttpStatus status) {
        this.status = status;
    }

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
