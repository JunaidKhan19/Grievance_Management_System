package com.gms.exceptions;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    /* ===============================
       CUSTOM AUTH / BUSINESS ERRORS
       =============================== */

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Map<String, String>> handleInvalidCredentials(InvalidCredentialsException ex) {
        Map<String, String> resp = new HashMap<>();
        resp.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resp);
    }

    @ExceptionHandler(AuthKeyRequiredException.class)
    public ResponseEntity<Map<String, String>> handleAuthKeyRequired(AuthKeyRequiredException ex) {
        Map<String, String> resp = new HashMap<>();
        resp.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
    }

    /* ===============================
       FALLBACK RUNTIME ERRORS
       =============================== */

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {
        Map<String, String> resp = new HashMap<>();
        resp.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Map<String, String>> handleThrowable(Throwable ex) {
        ex.printStackTrace(); // prints the full stack trace to the console
        Map<String, String> resp = new HashMap<>();
        resp.put("error", ex.getClass().getSimpleName());
        resp.put("details", ex.getMessage() != null ? ex.getMessage() : "No message available");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
    }


    /* ===============================
       VALIDATION ERRORS
       =============================== */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    /* ===============================
       DATABASE / ENTITY ERRORS
       =============================== */

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        Map<String, String> resp = new HashMap<>();
        resp.put("error", "Database constraint violation");
        resp.put("entity", detectEntity(ex));
        resp.put("details", ex.getMostSpecificCause().getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleHibernateConstraintViolation(ConstraintViolationException ex) {
        Map<String, String> resp = new HashMap<>();
        resp.put("error", "Hibernate constraint violation");
        resp.put("constraint", ex.getConstraintName());
        resp.put("details", ex.getSQLException().getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEntityNotFound(EntityNotFoundException ex) {
        Map<String, String> resp = new HashMap<>();
        resp.put("error", "Entity not found");
        resp.put("details", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp);
    }

    @ExceptionHandler(PersistenceException.class)
    public ResponseEntity<Map<String, String>> handlePersistenceException(PersistenceException ex) {
        Map<String, String> resp = new HashMap<>();
        resp.put("error", "Persistence / mapping error");
        resp.put("entity", detectEntity(ex));
        resp.put("details", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
    }

    /* ===============================
       FALLBACK / UNHANDLED ERRORS
       =============================== */

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleOtherExceptions(Exception ex) {
        Map<String, String> resp = new HashMap<>();
        resp.put("error", "Internal server error");
        resp.put("details", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
    }

    /* ===============================
       ENTITY DETECTOR
       =============================== */

    private String detectEntity(Exception ex) {
        String msg = ex.getMessage() != null ? ex.getMessage().toLowerCase() : "";

        if (msg.contains("grievances")) return "Grievance";
        if (msg.contains("officers")) return "Officer";
        if (msg.contains("employees")) return "Employee";
        if (msg.contains("categories")) return "Category";
        if (msg.contains("investigations")) return "Investigation";
        if (msg.contains("appeals")) return "Appeal";
        if (msg.contains("resolutions")) return "Resolution";

        return "Unknown (check entity mappings)";
    }
}
