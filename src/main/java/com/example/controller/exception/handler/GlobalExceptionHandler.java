package com.example.controller.exception.handler;

import com.example.exception.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<String> handleEmployeeNotFoundException(EmployeeNotFoundException stdException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(stdException.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleAllException(RuntimeException runException){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(runException.getMessage());
    }
}
