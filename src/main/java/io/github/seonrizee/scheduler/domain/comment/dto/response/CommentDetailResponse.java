package io.github.seonrizee.scheduler.domain.comment.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 댓글 상세 정보를 담는 응답 DTO.
 */
@Getter
@AllArgsConstructor
public class CommentDetailResponse {

    /**
     * 댓글 ID.
     */
    private final Long id;
    /**
     * 댓글 내용.
     */
    private final String content;
    /**
     * 작성자 ID.
     */
    private final Long userId;
    /**
     * 작성자 이름.
     */
    private final String username;
    /**
     * 생성 일시.
     */
    private final LocalDateTime createdAt;
    /**
     * 수정 일시.
     */
    private final LocalDateTime updatedAt;
}
