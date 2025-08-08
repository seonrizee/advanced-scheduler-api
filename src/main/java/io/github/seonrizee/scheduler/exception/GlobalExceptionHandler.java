package io.github.seonrizee.scheduler.exception;

import io.github.seonrizee.scheduler.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomBusinessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<Void> handleBusinessException(CustomBusinessException e) {
        return ApiResponse.error(e.getErrorCode().getCode(), e.getErrorCode().getMessage());
    }
}
