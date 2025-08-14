package io.github.seonrizee.scheduler.global.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * 에러 코드를 정의하는 열거형.
 */
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
    NO_PERMISSION(HttpStatus.FORBIDDEN, "EU-004", "권한이 없습니다."),

    // Error-General
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "EG-001", "서버 내부 오류가 발생했습니다."),
    METHOD_ARGUMENT_NOT_VALID(HttpStatus.BAD_REQUEST, "EG-002", "입력이 잘못되었습니다. %s"),
    INCONSISTENT_SESSION_STATE(HttpStatus.INTERNAL_SERVER_ERROR, "EG-003", "세션 상태가 일치하지 않습니다. 관리자에게 문의하세요.");

    /**
     * HTTP 상태 코드.
     */
    private final HttpStatus httpStatus;
    /**
     * 에러 코드.
     */
    private final String code;
    /**
     * 에러 메시지.
     */
    private final String message;

    /**
     * 메시지 포맷팅.
     *
     * @param args 포맷 인자
     * @return 포맷팅된 메시지
     */
    public String getMessage(String... args) {
        return String.format(this.message, (Object[]) args);
    }

}
