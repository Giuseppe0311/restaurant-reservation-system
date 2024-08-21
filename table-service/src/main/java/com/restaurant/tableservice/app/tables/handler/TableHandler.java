package com.restaurant.tableservice.app.tables.handler;

import com.restaurant.tableservice.app.tables.exception.TableAlreadyExist;
import com.restaurant.tableservice.app.tables.exception.TableNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class TableHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError ->
                errors.put(fieldError.getField(), fieldError.getDefaultMessage()));

        ErrorResponse errorResponse = new ErrorResponse(errors, HttpStatus.BAD_REQUEST);
        return ResponseEntity.badRequest().body(errorResponse);
    }
    @ExceptionHandler(TableNotFound.class)
    public ResponseEntity<ErrorResponse> handleTableNotFoundException(TableNotFound ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(errors, HttpStatus.NOT_FOUND);
        return ResponseEntity.badRequest().body(errorResponse);
    }
    @ExceptionHandler(TableAlreadyExist.class)
    public ResponseEntity<ErrorResponse> handleTableAlreadyExistException(TableAlreadyExist ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(errors, HttpStatus.BAD_REQUEST);
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
