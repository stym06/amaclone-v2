package com.personal.amacloneserver.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class AmacloneExceptionHandler {

    @ExceptionHandler({CustomerNotFoundException.class, CategoryNotFoundException.class})
    public ResponseEntity<Map<String, String>> handleNotFoundExceptions(Exception e) {
        log.error("Error occurred: {}", e.getMessage());
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMap);
    }

    @ExceptionHandler({InvalidCredentialsException.class, AccessDeniedException.class})
    public ResponseEntity<Map<String, String>> handleBadRequests(Exception e) {
        log.error("Error occurred: {}", e.getMessage());
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMap);
    }

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleAlreadyExists(Exception e) {
        log.error("Error occurred: {}", e.getMessage());
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMap);
    }
}
