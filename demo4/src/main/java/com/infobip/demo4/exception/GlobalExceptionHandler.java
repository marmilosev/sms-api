package com.infobip.demo4.exception;

import com.infobip.demo4.controller.dto.ApiResponse;
import org.modelmapper.MappingException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ApiResponse apiResponse;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ApiResponse>> handleValidationErrors(MethodArgumentNotValidException ex) {
       // List<String> errors = ex.getBindingResult().getFieldErrors()
       //         .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("lang/messages");
       // Locale en= new Locale("en");

        List<ApiResponse> errors = new ArrayList<>();
        for (var e: ex.getBindingResult().getFieldErrors()) {
            apiResponse= new ApiResponse(
                    messageSource.getMessage(e.getDefaultMessage() + "Code",null, LocaleContextHolder.getLocale()),
                    messageSource.getMessage(e.getDefaultMessage() + "Message",null,LocaleContextHolder.getLocale()),
                    messageSource.getMessage(e.getDefaultMessage() + "DocsURL",null,LocaleContextHolder.getLocale()),
                    null
                    //messageSource.getMessage(e.getDefaultMessage() + "timestamp", null, LocaleContextHolder.getLocale())
                    );
//            apiResponse.setCode();
//            apiResponse.setMessage();
//            apiResponse.setDocsURL();
            errors.add(apiResponse);
        }

        return new ResponseEntity<>(errors, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Map<String, List<String>>> handleNotFoundException(UsernameNotFoundException ex) {
        List<String> errors = Collections.singletonList(ex.getClass().getName());
//        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    //throw invalid ID
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Map<String, List<String>>> handleGeneralExceptions(Exception ex) {
        List<String> errors = Collections.singletonList(ex.getClass().getName());
//        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<Map<String, List<String>>> handleRuntimeExceptions(RuntimeException ex) {
//        List<String> errors = Collections.singletonList(ex.getMessage());
        List<String> errors = Collections.singletonList(ex.getClass().getName());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public final ResponseEntity<ApiResponse> handleInvalidDataAccessApiException(InvalidDataAccessApiUsageException ex) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode("8");
        apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/messages/v1/8");
        apiResponse.setMessage("User id must be valid.");
        // User id cannot be 0.
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public final ResponseEntity<ApiResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode("9");
        apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/messages/v1/9");
        apiResponse.setMessage("User id must be valid.");
        //User id not found in database. Please provide id that is stored in database.
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(MappingException.class)
    public final ResponseEntity<ApiResponse> handleMappingException(MappingException ex) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode("10");
        apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/messages/v1/6");
        apiResponse.setMessage("Date and time should be in format 'yyyy-MM-dd HH:mm'");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(NullPointerException.class)
    public final ResponseEntity<ApiResponse> handleNullPointerException(NullPointerException ex) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode("1");
        apiResponse.setDocsURL("https://mmilosevic-diplomski-api.com/authenticate/v1/1");
        apiResponse.setMessage("Username and/or password not correct.");
        //User id not found in database. Please provide id that is stored in database.
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }



    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }
}
