package com.lacakio.fleetgpstracking.exception;

import com.lacakio.fleetgpstracking.util.StringUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleValidationExceptions(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        List<Map<String, Object>> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(err -> {
                    Map<String, Object> errorMap = new HashMap<>();
                    errorMap.put("field", StringUtil.toSnakeCase(err.getField()));
                    errorMap.put("message", err.getDefaultMessage());
                    errorMap.put("rejectedValue", err.getRejectedValue());
                    return errorMap;
                })
                .collect(Collectors.toList());

        return Map.of(
                "status", HttpStatus.BAD_REQUEST.value(),
                "errors", errors,
                "path", request.getRequestURI(),
                "timestamp", LocalDateTime.now().toString()
        );
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handleNotFoundException(
            NotFoundException ex, HttpServletRequest request) {
        return Map.of(
                "status", HttpStatus.NOT_FOUND.value(),
                "error", "Not Found",
                "message", ex.getMessage(),
                "path", request.getRequestURI(),
                "timestamp", LocalDateTime.now().toString()
        );
    }

    @ExceptionHandler(DateTimeParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleDateTimeParseException(
            DateTimeParseException ex, HttpServletRequest request) {

        Map<String, Object> error = Map.of(
                "field", "from / to",
                "message", "Invalid date format, expected yyyy-MM-dd'T'HH:mm:ss",
                "rejectedValue", ex.getParsedString()
        );

        return Map.of(
                "status", HttpStatus.BAD_REQUEST.value(),
                "errors", List.of(error),
                "path", request.getRequestURI(),
                "timestamp", LocalDateTime.now().toString()
        );
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleMissingParams(MissingServletRequestParameterException ex, HttpServletRequest request) {
        Map<String, Object> error = new HashMap<>();
        error.put("field", ex.getParameterName() != null ? ex.getParameterName() : "unknown");
        error.put("message", "Required request parameter is missing");
        error.put("rejectedValue", null);

        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("errors", List.of(error));
        response.put("path", request.getRequestURI());
        response.put("timestamp", LocalDateTime.now().toString());

        return response;
    }


}