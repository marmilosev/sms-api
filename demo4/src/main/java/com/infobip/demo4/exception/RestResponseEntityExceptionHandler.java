package com.infobip.demo4.exception;

import com.infobip.demo4.controller.dto.ApiResponse;
import org.springframework.http.*;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

//    private ApiResponse apiResponse;

    //to handle other exceptions
    @ExceptionHandler(value
            = { IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
    @ExceptionHandler(value = { MissingRequestValueException.class })
    public ResponseEntity<ApiResponse> handleMissingServletRequestParameter(MissingServletRequestParameterException e, WebRequest webRequest){
        String parameterName = e.getParameterName();
        int code = e.getStatusCode().value();
        String message = parameterName + " is missing. Please provide " + parameterName + ". ";
        String docsUrl = "https://mmilosevic-diplomski-api.com/users/v1/5";
        ApiResponse apiResponse = new ApiResponse(code, message, docsUrl);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }
}
