package io.github.seonrizee.scheduler.dto;


import io.github.seonrizee.scheduler.common.code.SuccessCode;
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

    public static ResponseEntity<ApiResponse<Void>> error(HttpStatus status, String code, String message) {
        return new ResponseEntity<>(new ApiResponse<>(code, message, null), status);
    }

    public static <T> ResponseEntity<ApiResponse<T>> created(T responseDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(SuccessCode.CREATED.getCode(), SuccessCode.CREATED.getMessage(), responseDto));
    }

    public static <T> ResponseEntity<ApiResponse<T>> ok(T responseDto) {
        return ResponseEntity.ok(new ApiResponse<>(SuccessCode.OK.getCode(), SuccessCode.OK.getMessage(), responseDto));
    }

    public static ResponseEntity<ApiResponse<Void>> ok() {
        return ResponseEntity.ok(new ApiResponse<>(SuccessCode.OK.getCode(), SuccessCode.OK.getMessage(), null));
    }
}
