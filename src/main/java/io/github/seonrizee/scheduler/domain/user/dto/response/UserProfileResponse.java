package io.github.seonrizee.scheduler.domain.user.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 사용자 프로필 조회를 위한 응답 DTO.
 */
@Getter
@AllArgsConstructor
public class UserProfileResponse {

    /**
     * 사용자 ID.
     */
    private final Long id;
    /**
     * 사용자 이름.
     */
    private final String username;
    /**
     * 이메일.
     */
    private final String email;
    /**
     * 생성 일시.
     */
    private final LocalDateTime createdAt;
    /**
     * 수정 일시.
     */
    private final LocalDateTime updatedAt;
}
