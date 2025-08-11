package io.github.seonrizee.scheduler.common.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // Error-Schedule
    SCHEDULE_NOT_FOUND(HttpStatus.NOT_FOUND, "ES-001", "일정이 존재하지 않습니다."),

    // Error-Comment
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "EC-001", "댓글이 존재하지 않습니다."),

    // Error-User
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "EU-001", "사용자가 존재하지 않습니다."),
    USER_NOT_AUTHORIZED(HttpStatus.UNAUTHORIZED, "EU-002", "로그인이 필요합니다."),
    INVALID_USER(HttpStatus.UNAUTHORIZED, "EU-003", "입력한 정보를 다시 확인해주세요."),

    // Error-General
    METHOD_ARGUMENT_NOT_VALID(HttpStatus.BAD_REQUEST, "EG-001", "입력이 잘못되었습니다. %s");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    public String getMessage(String... args) {
        return String.format(this.message, (Object[]) args);
    }

}
