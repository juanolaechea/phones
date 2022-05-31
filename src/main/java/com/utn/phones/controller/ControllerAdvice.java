package com.utn.phones.controller;


import com.utn.phones.exceptions.ElementDoesNotExistsException;
import com.utn.phones.exceptions.ElementExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ElementDoesNotExistsException.class)
    public ResponseEntity processEntityDoesNotExists() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(ElementExistsException.class)
    public ResponseEntity processEntityExists() {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }



}
