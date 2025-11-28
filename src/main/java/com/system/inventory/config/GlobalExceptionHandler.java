package com.system.inventory.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

// Esta configuracion solo captura los errores de validacion de escritura establecidas en el DTO
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
    String[] errors = ex.getBindingResult().getAllErrors().stream()
        .map(error -> error.getDefaultMessage())
        .toArray(String[]::new);

    return new ResponseEntity<>(Map.of("errors", errors), HttpStatus.BAD_REQUEST);
  }
}