package com.scanai.api.infra.exceptions;

import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class RestExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> constraintViolationSql(Exception ex){
        ApiErrorResponse response = new ApiErrorResponse(
                ex.getMessage(),
                "Internal Server Error",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ApiErrorResponse> constraintViolationSql(SQLIntegrityConstraintViolationException ex){
        ApiErrorResponse response = new ApiErrorResponse(
                ex.getMessage(),
                "Constraint Violation",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiErrorResponse> dataIntegrityViolationSql(DataIntegrityViolationException ex){
        ApiErrorResponse response = new ApiErrorResponse(
                ex.getMostSpecificCause().getMessage(),
                "Data integrity Violation",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> entityNotFound(EntityNotFoundException ex){
        ApiErrorResponse response = new ApiErrorResponse(
                ex.getMessage(),
                "Entity With This Id Not Found",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
