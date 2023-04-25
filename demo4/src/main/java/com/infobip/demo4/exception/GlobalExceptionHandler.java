package com.infobip.demo4.exception;

import com.infobip.demo4.controller.dto.ApiResponse;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;
import java.util.stream.Collectors;

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
                    messageSource.getMessage(e.getDefaultMessage() + "DocsURL",null,LocaleContextHolder.getLocale()),null
//                    ,
//                    messageSource.getMessage(e.getDefaultMessage() + "Timestamp", null, LocaleContextHolder.getTimeZone())
                    );
          //  apiResponse.setCode();
          //  apiResponse.setMessage();
         //   apiResponse.setDocsURL();
            errors.add(apiResponse);
        }

        return new ResponseEntity<>(errors, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Map<String, List<String>>> handleNotFoundException(UsernameNotFoundException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    //throw invalid ID
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Map<String, List<String>>> handleGeneralExceptions(Exception ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<Map<String, List<String>>> handleRuntimeExceptions(RuntimeException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }
}
