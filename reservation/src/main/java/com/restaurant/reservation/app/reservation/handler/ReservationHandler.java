package com.restaurant.reservation.app.reservation.handler;

import com.restaurant.reservation.app.reservation.exceptions.ReservationNotFound;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ReservationHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError ->
                errors.put(fieldError.getField(), fieldError.getDefaultMessage()));

        ErrorResponse errorResponse = new ErrorResponse(errors, HttpStatus.BAD_REQUEST);
        return ResponseEntity.badRequest().body(errorResponse);
    }
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ErrorResponse> handleFeignException(FeignException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(errors, HttpStatus.BAD_REQUEST);
        return ResponseEntity.badRequest().body(errorResponse);
    }
    @ExceptionHandler(ReservationNotFound.class)
    public ResponseEntity<ErrorResponse> handleTableNotFoundException(ReservationNotFound ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(errors, HttpStatus.NOT_FOUND);
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
