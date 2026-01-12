package com.fullstack.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalCustomExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> getCustomException(RecordNotFoundException e){

        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

    }
}
