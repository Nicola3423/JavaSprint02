package com.example.cp01.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PacienteNotFoundException.class)
    public ResponseEntity<Map<String, String>> handlePacienteNotFound(PacienteNotFoundException ex) {
        return createResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(TratamentoNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleTratamentoNotFound(TratamentoNotFoundException ex) {
        return createResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(SintomaNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleSintomaNotFound(SintomaNotFoundException ex) {
        return createResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneralException(Exception ex) {
        return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro inesperado");
    }

    @ExceptionHandler(MedicoNotFoundException.class)
    public ResponseEntity<String> handleMedicoNotFoundException(MedicoNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(FeedbackNotFoundException.class)
    public ResponseEntity<String> handleFeedbackNotFoundException(FeedbackNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    private ResponseEntity<Map<String, String>> createResponse(HttpStatus status, String message) {
        Map<String, String> response = new HashMap<>();
        response.put("status", status.toString());
        response.put("message", message);
        return new ResponseEntity<>(response, status);
    }
}
