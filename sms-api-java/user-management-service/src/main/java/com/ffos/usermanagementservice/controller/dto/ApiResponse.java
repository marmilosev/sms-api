package com.ffos.usermanagementservice.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {
    private String code;
    private String message;
    private String docsURL;

    public ApiResponse() {

    }

    public ApiResponse(String code, String message, String docsURL) {
        this.code = code;
        this.message = message;
        this.docsURL = docsURL;
    }

}
