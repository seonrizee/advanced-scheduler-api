package io.github.seonrizee.scheduler.domain.schedule.dto.response;

/**
 * 일정 목록 조회를 위한 응답 DTO 인터페이스.
 * JPQL 프로젝션을 사용하여 필요한 데이터만 선택적으로 조회합니다.
 */
public interface SchedulePageResponse {
    /**
     * @return 일정 ID
     */
    Long getId();

    /**
     * @return 일정 요약
     */
    String getSummary();

    /**
     * @return 일정 상세 설명
     */
    String getDescription();

    /**
     * @return 댓글 수
     */
    Long getCommentCount();

    /**
     * @return 작성자 ID
     */
    Long getUserId();

    /**
     * @return 작성자 이름
     */
    String getUsername();

    /**
     * @return 생성 일시
     */
    java.time.LocalDateTime getCreatedAt();

    /**
     * @return 수정 일시
     */
    java.time.LocalDateTime getUpdatedAt();
}
