package io.github.seonrizee.scheduler.global.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SuccessCode {

    // Success-Common
    OK("SC-200", "요청이 성공적으로 처리되었습니다."),
    CREATED("SC-201", "리소스가 성공적으로 생성되었습니다.");

    private final String code;
    private final String message;
}
