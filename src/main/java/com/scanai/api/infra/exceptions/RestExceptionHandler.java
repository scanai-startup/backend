package com.scanai.api.infra.exceptions;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class RestExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> globalError(Exception ex){
        ApiErrorResponse response = new ApiErrorResponse(
                ex.getMessage(),
                "Internal Server Error",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiErrorResponse> badCredentialsError(BadCredentialsException ex){
        ApiErrorResponse response = new ApiErrorResponse(
                ex.getMessage(),
                "Incorrect User or Password",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
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

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiErrorResponse> noResourceFound(NoResourceFoundException ex){
        ApiErrorResponse response = new ApiErrorResponse(
                ex.getMessage(),
                "No Resource Found With This id",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
