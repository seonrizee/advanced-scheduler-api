package io.github.seonrizee.scheduler.global.exception;

import io.github.seonrizee.scheduler.global.code.ErrorCode;
import io.github.seonrizee.scheduler.global.dto.ApiResponse;
import java.util.Objects;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 전역 예외 처리를 담당하는 클래스.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 처리되지 않은 모든 예외를 처리합니다.
     * @param e 발생한 예외
     * @return 내부 서버 오류 응답
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception e) {
        return ApiResponse.error(
                ErrorCode.INTERNAL_SERVER_ERROR.getHttpStatus(),
                ErrorCode.INTERNAL_SERVER_ERROR.getCode(),
                ErrorCode.INTERNAL_SERVER_ERROR.getMessage()
        );
    }

    /**
     * 비즈니스 로직 예외를 처리합니다.
     * @param e 발생한 비즈니스 예외
     * @return 해당 비즈니스 예외에 맞는 오류 응답
     */
    @ExceptionHandler(CustomBusinessException.class)
    public ResponseEntity<ApiResponse<Void>> handleBusinessException(CustomBusinessException e) {
        return ApiResponse.error(
                e.getErrorCode().getHttpStatus(),
                e.getErrorCode().getCode(),
                e.getErrorCode().getMessage()
        );
    }

    /**
     * 유효성 검사 예외를 처리합니다.
     * @param e 발생한 유효성 검사 예외
     * @return 잘못된 요청 오류 응답
     */
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
