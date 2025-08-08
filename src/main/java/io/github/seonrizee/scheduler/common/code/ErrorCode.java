package io.github.seonrizee.scheduler.common.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // Error-Schedule
    SCHEDULE_NOT_FOUND("ES-001", "일정이 존재하지 않습니다.");
    
    private final String code;
    private final String message;
}
