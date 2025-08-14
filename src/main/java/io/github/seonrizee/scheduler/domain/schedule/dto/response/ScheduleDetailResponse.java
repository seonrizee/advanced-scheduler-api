package io.github.seonrizee.scheduler.domain.schedule.dto.response;

import io.github.seonrizee.scheduler.domain.comment.dto.response.CommentDetailResponse;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 일정 상세 정보를 담는 응답 DTO.
 */
@Getter
@AllArgsConstructor
public class ScheduleDetailResponse {

    /**
     * 일정 ID.
     */
    private final Long id;
    /**
     * 일정 요약.
     */
    private final String summary;
    /**
     * 일정 상세 설명.
     */
    private final String description;
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
    /**
     * 일정에 달린 댓글 목록.
     */
    private final List<CommentDetailResponse> comments;
}
