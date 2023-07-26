package com.example.mydemoproject.authservice.exception;

import com.example.mydemoproject.authservice.dto.ApiResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ApiResponse apiResponse;

//    @ExceptionHandler(NullPointerException.class)
//    public final ResponseEntity<ApiResponse> handleNullPointerException(NullPointerException ex) {
//        ApiResponse apiResponse = new ApiResponse();
//        apiResponse.setCode("1");
//        apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/authenticate/v1/1");
//        apiResponse.setMessage("Username and/or password not correct.");
//        //User id not found in database. Please provide id that is stored in database.
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
//    }
}
