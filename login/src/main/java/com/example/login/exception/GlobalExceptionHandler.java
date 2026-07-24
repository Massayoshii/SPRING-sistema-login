package com.example.login.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<Object> handlerUserAlreadyExist(UserAlreadyExistException exception){
        Map<String , Object> body = new LinkedHashMap<>();
        body.put("timestamp" , LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "user already exist");
        body.put("message",exception.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handlerUserNotFound(UserNotFoundException ex){
        Map<String , Object> body = new LinkedHashMap<>();
        body.put("timestamp" , LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", "user not found");
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handlerGenericException(Exception ex){
        Map<String , Object> body = new LinkedHashMap<>();
        body.put("timestamp" , LocalDateTime.now());
        body.put("status" , HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("error" , "erro interno no servidor");
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body , HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
