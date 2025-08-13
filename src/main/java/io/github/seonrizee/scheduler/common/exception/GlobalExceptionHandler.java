package io.github.seonrizee.scheduler.common.exception;

import io.github.seonrizee.scheduler.common.code.ErrorCode;
import io.github.seonrizee.scheduler.dto.ApiResponse;
import java.util.Objects;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception e) {
        return ApiResponse.error(
                ErrorCode.INTERNAL_SERVER_ERROR.getHttpStatus(),
                ErrorCode.INTERNAL_SERVER_ERROR.getCode(),
                ErrorCode.INTERNAL_SERVER_ERROR.getMessage()
        );
    }

    @ExceptionHandler(CustomBusinessException.class)
    public ResponseEntity<ApiResponse<Void>> handleBusinessException(CustomBusinessException e) {
        return ApiResponse.error(
                e.getErrorCode().getHttpStatus(),
                e.getErrorCode().getCode(),
                e.getErrorCode().getMessage()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationException(MethodArgumentNotValidException e) {
        return ApiResponse.error(
                ErrorCode.METHOD_ARGUMENT_NOT_VALID.getHttpStatus(),
                ErrorCode.METHOD_ARGUMENT_NOT_VALID.getCode(),
                ErrorCode.METHOD_ARGUMENT_NOT_VALID.getMessage(
                        Objects.requireNonNull(
                                Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage())
                ));
    }

}
