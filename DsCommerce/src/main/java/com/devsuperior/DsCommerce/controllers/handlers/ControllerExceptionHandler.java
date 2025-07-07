package com.devsuperior.DsCommerce.controllers.handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.DsCommerce.DTO.CustomError;
import com.devsuperior.DsCommerce.services.Exceptions.DatabaseException;
import com.devsuperior.DsCommerce.services.Exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice

public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> ResourceNotFoundException(DatabaseException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
       return ResponseEntity.status(status).body(err);
    }

     @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<CustomError> database(DatabaseException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
       return ResponseEntity.status(status).body(err);
    }
}


