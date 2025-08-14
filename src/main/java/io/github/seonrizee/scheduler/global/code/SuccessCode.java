package io.github.seonrizee.scheduler.global.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 성공 코드를 정의하는 열거형.
 */
@Getter
@RequiredArgsConstructor
public enum SuccessCode {

    // Success-Common
    OK("SC-200", "요청이 성공적으로 처리되었습니다."),
    CREATED("SC-201", "리소스가 성공적으로 생성되었습니다.");

    /**
     * 성공 코드.
     */
    private final String code;
    /**
     * 성공 메시지.
     */
    private final String message;
}
