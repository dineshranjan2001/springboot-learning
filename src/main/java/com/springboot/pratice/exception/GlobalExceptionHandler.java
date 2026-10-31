package com.springboot.pratice.exception;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.method.MethodValidationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // DTO and Entity exception
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest req){
        //Map<String,String> errors = new HashMap<>();
//        ex.getBindingResult().getFieldErrors().forEach(error->{
//            errors.putIfAbsent(error.getField(),error.getDefaultMessage());
//        });
        Map<String,String> errors=ex.getBindingResult().getFieldErrors()
                .stream().collect(Collectors.toMap(
                        FieldError::getField,
                        DefaultMessageSourceResolvable::getDefaultMessage,
                        (existing,replacement)->existing,
                        HashMap::new
                ));
        ErrorResponse errorResponse=new ErrorResponse(
                false,
                HttpStatus.BAD_REQUEST.value(),
                "Validation Failed",
                "Please check the request fields",
                req.getRequestURI(),
                Instant.now(),
                errors
        );

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    // path variable and method parameter validation
    @ExceptionHandler(MethodValidationException.class)
    public ResponseEntity<ErrorResponse> handleMehtodValidationException(MethodValidationException ex, HttpServletRequest req){
        ErrorResponse errorResponse=new ErrorResponse(
                false,
                HttpStatus.BAD_REQUEST.value(),
                ex.getClass().getSimpleName(),
                ex.getMessage(),
                req.getRequestURI(),
                Instant.now(),
                Map.of()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest req){
        ErrorResponse errorResponse=new ErrorResponse(
                false,
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                ex.getMessage(),
                req.getRequestURI(),
                Instant.now(),
                Map.of()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(ResourceExistsException.class)
    public ResponseEntity<ErrorResponse> handleResourceExistsException(ResourceExistsException ex,HttpServletRequest req){
        ErrorResponse errorResponse=new ErrorResponse(
                false,
                HttpStatus.CONFLICT.value(),
                "Conflict",
                ex.getMessage(),
                req.getRequestURI(),
                Instant.now(),
                Map.of()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    // Database constraint violations
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex,HttpServletRequest req){
        ErrorResponse errorResponse=new ErrorResponse(
                false,
                HttpStatus.CONFLICT.value(),
                "Data Conflict",
                "The operation violates a database constraint",
                req.getRequestURI(),
                Instant.now(),
                Map.of()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    // Invalid JSON or incorrect JSON data types
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex,HttpServletRequest req){
        ErrorResponse errorResponse=new ErrorResponse(
                false,
                HttpStatus.CONFLICT.value(),
                "Invalid request body",
                "Request body is missing or contains invalid JSON",
                req.getRequestURI(),
                Instant.now(),
                Map.of()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex,HttpServletRequest req){
        ErrorResponse errorResponse=new ErrorResponse(
                false,
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getClass().getSimpleName(),
                "Something went wrong or Internal Server Error",
                req.getRequestURI(),
                Instant.now(),
                Map.of()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
