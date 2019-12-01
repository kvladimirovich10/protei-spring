package com.protei.spring.controller;


import com.protei.spring.exception.FieldContentException;
import com.protei.spring.exception.InvalidStatusException;
import com.protei.spring.exception.UserAlreadyExistsException;
import com.protei.spring.exception.UserNotFoundException;
import com.protei.spring.response.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ExceptionController {


    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> handleRunTimeException(RuntimeException e) {
        ExceptionResponse response = new ExceptionResponse(e.getClass().getSimpleName(), e.getMessage());

        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<Object> userNotFondExceptionHandler(UserNotFoundException e) {
        ExceptionResponse response = new ExceptionResponse(e.getClass().getSimpleName(), e.getMessage());

        return ResponseEntity.status(NOT_FOUND).body(response);
    }

    @ExceptionHandler({FieldContentException.class})
    public ResponseEntity<Object> fieldContentExceptionHandler(FieldContentException e) {

        ExceptionResponse response = new ExceptionResponse(e.getClass().getSimpleName());

        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            response.addExceptionMessage(fieldError.getField() + " : " +fieldError.getDefaultMessage());
        }

        return ResponseEntity.status(BAD_REQUEST).body(response);

    }

    @ExceptionHandler(InvalidStatusException.class)
    public ResponseEntity<Object> handleException(InvalidStatusException e) {
        ExceptionResponse response = new ExceptionResponse(e.getClass().getSimpleName(), e.getMessage());

        return ResponseEntity.status(BAD_REQUEST).body(response);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Object> handleException(UserAlreadyExistsException e) {
        ExceptionResponse response = new ExceptionResponse(e.getClass().getSimpleName(), e.getMessage());

        return ResponseEntity.status(BAD_REQUEST).body(response);
    }
}
