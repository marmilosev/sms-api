package com.infobip.demo4.controller.dto;

import com.infobip.model.SmsResponseDetails;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.List;

public class ApiResponse {
    private String code;
    private String message;

    private String docsURL;

    private List<SmsResponseDetails> smsResponseDetails;

    private Date date;

    public ApiResponse() {

    }

    public ApiResponse(String code, String message, String docsURL, List<SmsResponseDetails> smsResponseDetails) {
        this.code = code;
        this.message = message;
        this.docsURL = docsURL;
        this.smsResponseDetails = smsResponseDetails;
//        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
