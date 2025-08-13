package io.github.seonrizee.scheduler.global.dto;


import io.github.seonrizee.scheduler.global.code.SuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {

    private final String code;
    private final String message;
    private final T data;

    public static ResponseEntity<ApiResponse<Void>> error(HttpStatus httpStatus, String code, String message) {
        return new ResponseEntity<>(new ApiResponse<>(code, message, null), httpStatus);
    }

    public static <T> ApiResponse<T> created(T responseDto) {
        return new ApiResponse<>(SuccessCode.CREATED.getCode(), SuccessCode.CREATED.getMessage(), responseDto);
    }

    public static <T> ApiResponse<T> ok(T responseDto) {
        return new ApiResponse<>(SuccessCode.OK.getCode(), SuccessCode.OK.getMessage(), responseDto);
    }

    public static ApiResponse<Void> ok() {
        return new ApiResponse<>(SuccessCode.OK.getCode(), SuccessCode.OK.getMessage(), null);
    }
}
