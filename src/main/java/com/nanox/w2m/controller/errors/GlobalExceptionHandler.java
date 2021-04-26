package com.nanox.w2m.controller.errors;

import com.nanox.w2m.exceptions.InvalidInputException;
import com.nanox.w2m.exceptions.SuperHeroNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {InvalidInputException.class})
    public ResponseEntity<ApiError> handleInvalidInputException(InvalidInputException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.badRequest().body(apiError);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleException(Exception ex) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return ResponseEntity.badRequest().body(apiError);
    }

    @ExceptionHandler(value = {SuperHeroNotFoundException.class})
    public ResponseEntity<Object> handleSuperHeroNotFoundException(SuperHeroNotFoundException ex) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return ResponseEntity.badRequest().body(apiError);
    }

}
