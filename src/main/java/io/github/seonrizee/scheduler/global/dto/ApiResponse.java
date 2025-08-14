package io.github.seonrizee.scheduler.global.dto;


import io.github.seonrizee.scheduler.global.code.SuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * API 응답을 위한 공통 래퍼 클래스.
 * @param <T> 응답 데이터의 타입
 */
@Getter
@AllArgsConstructor
public class ApiResponse<T> {

    /**
     * 응답 코드.
     */
    private final String code;
    /**
     * 응답 메시지.
     */
    private final String message;
    /**
     * 응답 데이터.
     */
    private final T data;

    /**
     * 에러 응답을 생성합니다.
     * @param httpStatus HTTP 상태 코드
     * @param code 에러 코드
     * @param message 에러 메시지
     * @return ResponseEntity<ApiResponse<Void>> 객체
     */
    public static ResponseEntity<ApiResponse<Void>> error(HttpStatus httpStatus, String code, String message) {
        return new ResponseEntity<>(new ApiResponse<>(code, message, null), httpStatus);
    }

    /**
     * 'Created' 성공 응답을 생성합니다.
     * @param responseDto 응답 데이터
     * @return ApiResponse 객체
     * @param <T> 응답 데이터의 타입
     */
    public static <T> ApiResponse<T> created(T responseDto) {
        return new ApiResponse<>(SuccessCode.CREATED.getCode(), SuccessCode.CREATED.getMessage(), responseDto);
    }

    /**
     * 'OK' 성공 응답을 생성합니다.
     * @param responseDto 응답 데이터
     * @return ApiResponse 객체
     * @param <T> 응답 데이터의 타입
     */
    public static <T> ApiResponse<T> ok(T responseDto) {
        return new ApiResponse<>(SuccessCode.OK.getCode(), SuccessCode.OK.getMessage(), responseDto);
    }

    /**
     * 데이터 없는 'OK' 성공 응답을 생성합니다.
     * @return ApiResponse 객체
     */
    public static ApiResponse<Void> ok() {
        return new ApiResponse<>(SuccessCode.OK.getCode(), SuccessCode.OK.getMessage(), null);
    }
}
