package io.github.seonrizee.scheduler.global.exception;

import io.github.seonrizee.scheduler.global.code.ErrorCode;
import lombok.Getter;

@Getter
public class CustomBusinessException extends RuntimeException {

    private final ErrorCode errorCode;

    public CustomBusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}
