package io.github.seonrizee.scheduler.global.exception;

import io.github.seonrizee.scheduler.global.code.ErrorCode;
import lombok.Getter;

/**
 * 비즈니스 로직 상의 예외를 나타내는 클래스.
 */
@Getter
public class CustomBusinessException extends RuntimeException {

    private final ErrorCode errorCode;

    /**
     * CustomBusinessException을 생성합니다.
     * @param errorCode 발생한 예외에 해당하는 에러 코드
     */
    public CustomBusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}
