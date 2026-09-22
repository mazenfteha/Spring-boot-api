package com.mazenfteha.rest_api.exception;

import java.util.stream.Collectors;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mazenfteha.rest_api.dto.ApiErrorResponse;
import com.mazenfteha.rest_api.dto.ValidationErrorResponse;


import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice 
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public  ResponseEntity<ApiErrorResponse> handleResourceNotFound(
        ResourceNotFoundException exception,
        HttpServletRequest request
    ) {
        ApiErrorResponse response = new ApiErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                request.getRequestURI());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<ValidationErrorResponse> handleValidationErrors(
        MethodArgumentNotValidException exception) {

    Map<String, String> fields = exception.getBindingResult()
            .getFieldErrors()
            .stream()
            .collect(Collectors.toMap(
                    error -> error.getField(),
                    error -> error.getDefaultMessage()));

    ValidationErrorResponse response = new ValidationErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Validation failed",
            fields);

    return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(response);
}

}


/*

Controller
    ↓
Service throws ResourceNotFoundException
    ↓
GlobalExceptionHandler catches it
    ↓
Returns JSON with HTTP 404

This prevents repeating try/catch blocks in every controller.
*/


/*
HTTP request
    ↓
@RequestBody is converted into DTO
    ↓
@Valid checks @NotBlank and @Size
    ↓
Validation fails
    ↓
MethodArgumentNotValidException is thrown
    ↓
GlobalExceptionHandler catches it
    ↓
Clean JSON response is returned
*/