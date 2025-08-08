package io.github.seonrizee.scheduler.exception;

import io.github.seonrizee.scheduler.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomBusinessException.class)
    public ResponseEntity<ApiResponse<Void>> handleBusinessException(CustomBusinessException e) {
        return ApiResponse.error(HttpStatus.NOT_FOUND, e.getErrorCode().getCode(), e.getErrorCode().getMessage());
    }
}
