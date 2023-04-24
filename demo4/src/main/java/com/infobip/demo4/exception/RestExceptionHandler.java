package com.infobip.demo4.exception;

import com.infobip.demo4.controller.dto.ApiResponse;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.validator.internal.IgnoreForbiddenApisErrors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Malformed JSON request";
        return buildResponseEntity(new ApiResponse(HttpStatus.BAD_REQUEST, error, ex));
    }

    private ResponseEntity<Object> buildResponseEntity(ApiResponse apiResponse) {
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            EntityNotFoundException ex) {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.NOT_FOUND);
        apiResponse.setMessage(ex.getMessage());
        return buildResponseEntity(apiResponse);
    }

}
