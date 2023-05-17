package com.infobip.userManagementService.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ApiResponse {
    private String code;
    private String message;

    private String docsURL;

    private Timestamp timestamp;

    public ApiResponse() {

    }

    public ApiResponse(String code, String message, String docsURL) {
        this.code = code;
        this.message = message;
        this.docsURL = docsURL;
        //this.timestamp = timestamp;
    }

}
