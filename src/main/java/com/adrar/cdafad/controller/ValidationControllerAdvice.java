package com.adrar.cdafad.controller;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ValidationControllerAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>>
    handleMethodArgumentNotValidException(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations()
                .forEach(cv -> errors.put(
                        cv.getPropertyPath().toString(), cv.getMessage()
                )
            );

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
