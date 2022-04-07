package com.courseapp.backend.controllers;

import com.courseapp.backend.annotations.AllowExceptionHandler;
import com.courseapp.backend.utils.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice(annotations = AllowExceptionHandler.class)
public class ExceptionHandlerController {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return returnErrorMessage(ErrorMessage.CONSTRAINT_VIOLATION.toString());
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleNullPointerException(NullPointerException e) {
        return returnErrorMessage(ErrorMessage.INVALID_OBJECT.toString());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleWrongParamsType(MethodArgumentTypeMismatchException e) {
        return returnErrorMessage(ErrorMessage.INVALID_IDENTIFIER_TYPE.toString());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> paramsSmallerThanOne(IllegalArgumentException e) {
        return returnErrorMessage(ErrorMessage.PAGE_PARAM_SHOULD_BE_GREATER_THAN_ZERO.toString());
    }

    private ResponseEntity<String> returnErrorMessage(String errorMessage) {
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

}
