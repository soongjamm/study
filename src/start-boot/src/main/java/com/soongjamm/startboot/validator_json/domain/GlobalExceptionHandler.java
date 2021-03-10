package com.soongjamm.startboot.validator_json.domain;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorResponse>> methodValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        return ResponseEntity.badRequest().body(createErrorResponse(bindingResult));
    }

    @ExceptionHandler(NotEqualToPasswordException.class)
    public ResponseEntity<ErrorResponse> notEqualToPasswordException(NotEqualToPasswordException e) {
        return ResponseEntity.badRequest().body(createErrorResponse("password", e.getMessage(), "secret"));
    }

    private ErrorResponse createErrorResponse(String field, String message, String rejectedValue) {
        return ErrorResponse.builder()
                .field(field)
                .message(message)
                .rejectedValue(rejectedValue)
                .build();
    }

    private List<ErrorResponse> createErrorResponse(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream()
                .map(x -> ErrorResponse.builder()
                        .field(x.getField())
                        .rejectedValue(x.getRejectedValue())
                        .message(x.getDefaultMessage())
                        .build())
                .collect(Collectors.toList());
    }


}
