package io.github.seonrizee.scheduler.domain.schedule.repository;

import io.github.seonrizee.scheduler.domain.schedule.dto.response.SchedulePageResponse;
import io.github.seonrizee.scheduler.domain.schedule.entity.Schedule;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Schedule 엔티티에 대한 데이터베이스 액세스를 제공하는 리포지토리.
 */
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    /**
     * ID로 일정을 조회하되, 연관된 댓글을 함께 페치 조인하여 가져옵니다.
     * @param id 조회할 일정의 ID
     * @return Optional<Schedule> 객체
     */
    @Query("SELECT s FROM Schedule s LEFT JOIN FETCH s.comments WHERE s.id = :id")
    Optional<Schedule> findWithCommentsById(@Param("id") Long id);

    /**
     * 모든 일정을 페이지네이션하여 조회하며, 각 일정의 댓글 수를 포함합니다.
     * @param pageable 페이지 정보
     * @return 일정 목록 페이지
     */
    @Query(value = """
            SELECT s.id AS id,
                    s.summary AS summary,
                    s.description AS description,
                    COUNT(c) AS commentCount,
                    u.id AS userId,
                    u.username AS username,
                    s.createdAt AS createdAt,
                    s.updatedAt AS updatedAt
            FROM Schedule s
            JOIN s.user u
            LEFT JOIN s.comments c
            GROUP BY s.id, u.id
            """,
            countQuery = "SELECT count(s) FROM Schedule s")
    Page<SchedulePageResponse> findWithCommentCount(Pageable pageable);
}
