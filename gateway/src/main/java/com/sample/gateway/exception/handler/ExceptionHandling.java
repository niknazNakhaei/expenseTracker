package com.sample.gateway.exception.handler;

import com.sample.gateway.exception.dto.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice(basePackages = "com.sample.gateway.incoming")
public class ExceptionHandling {

    @ExceptionHandler(value = IllegalArgumentException.class)
    public @ResponseBody ResponseEntity<ErrorResponseDto> handleBadRequestException(IllegalArgumentException ex, HttpServletRequest request) {
        return new ResponseEntity<>(buildErrorResponseDto(ex.getMessage(), request), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public @ResponseBody ResponseEntity<ErrorResponseDto> handleOtherRunException(RuntimeException ex, HttpServletRequest request) {
        return new ResponseEntity<>(buildErrorResponseDto(ex.getMessage(), request), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = Exception.class)
    public @ResponseBody ResponseEntity<ErrorResponseDto> handleOtherException(Exception ex, HttpServletRequest request) {
        return new ResponseEntity<>(buildErrorResponseDto(ex.getMessage(), request), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ErrorResponseDto buildErrorResponseDto(String message, HttpServletRequest request) {
        return new ErrorResponseDto(message, LocalDateTime.now().toString(), request.getRequestURI());
    }
}
