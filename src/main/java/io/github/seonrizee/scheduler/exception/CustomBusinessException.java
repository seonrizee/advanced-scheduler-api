package io.github.seonrizee.scheduler.exception;

import io.github.seonrizee.scheduler.common.code.ErrorCode;
import lombok.Getter;

@Getter
public class CustomBusinessException extends RuntimeException {

    private final ErrorCode errorCode;

    public CustomBusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}
