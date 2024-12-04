package com.keniu.exceptions;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getAllErrors().stream()
                .map(error -> {
                    if (error instanceof FieldError fielderror) {
                        return "Field: " + fielderror.getField() + " - "
                            + fielderror.getDefaultMessage();
                    } else {
                        return "Object: " + error.getObjectName()
                            + " - " + error.getDefaultMessage();
                    }
                })
                .toList();

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity<String> handleRegistrationException(RegistrationException ex) {
        return new ResponseEntity<>(
            "Can't create a new user. The reason: " + ex.getMessage(),
            HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<String> handleAuthorizationDeniedException(Exception ex) {
        return new ResponseEntity<>(
            "You don't have access!", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(EmptyShoppingCartException.class)
    public ResponseEntity<String> handleEmptyShoppingCartException(Exception ex) {
        return new ResponseEntity<>(
            "You cart is empty!", UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception ex) {
        return new ResponseEntity<>(
            "An unexpected error occurred", INTERNAL_SERVER_ERROR);
    }
}
