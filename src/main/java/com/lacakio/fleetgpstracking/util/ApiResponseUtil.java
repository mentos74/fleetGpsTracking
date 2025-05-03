package com.lacakio.fleetgpstracking.util;

import com.lacakio.fleetgpstracking.dto.ApiSuccessResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ApiResponseUtil {
    public static ResponseEntity<?> badRequest(String field, String message, String rejected, HttpServletRequest request) {
        Map<String, Object> error = Map.of(
                "field", field,
                "message", message,
                "rejectedValue", rejected
        );

        return ResponseEntity.badRequest().body(Map.of(
                "status", HttpStatus.BAD_REQUEST.value(),
                "errors", List.of(error),
                "path", request.getRequestURI(),
                "timestamp", LocalDateTime.now().toString()
        ));
    }

    public static  ResponseEntity<?> unauthorizedResponse(String message, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                Map.of(
                        "status", HttpStatus.UNAUTHORIZED.value(),
                        "error", "Unauthorized",
                        "message", message,
                        "path", request.getRequestURI(),
                        "timestamp", LocalDateTime.now().toString()
                )
        );
    }

    public static <T> ResponseEntity<ApiSuccessResponse<T>> successResponse(int status, String message, T data) {
        ApiSuccessResponse<T> response = new ApiSuccessResponse<>(status, message, data);
        return ResponseEntity.status(status).body(response);
    }

    public static <T> ResponseEntity<ApiSuccessResponse<T>> successResponse(String message, T data) {
        return successResponse(200, message, data);
    }

    public static ResponseEntity<ApiSuccessResponse<Void>> successResponse(String message) {
        return successResponse(200, message, null);
    }
}
