package io.github.seonrizee.scheduler.repository;

import io.github.seonrizee.scheduler.dto.response.SchedulePageResponse;
import io.github.seonrizee.scheduler.entity.Schedule;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("SELECT s FROM Schedule s LEFT JOIN FETCH s.comments WHERE s.id = :id")
    Optional<Schedule> findWithCommentsById(@Param("id") Long id);

    @Query(value = """
            SELECT new io.github.seonrizee.scheduler.dto.response.SchedulePageResponse(s.id,
                    s.summary,
                    s.description,
                    COUNT(c),
                    u.id,
                    u.username,
                    s.createdAt,
                    s.updatedAt
            )
            FROM Schedule s
            JOIN s.user u
            LEFT JOIN s.comments c
            GROUP BY s.id, u.id
            """,
            countQuery = "SELECT count(s) FROM Schedule s")
    Page<SchedulePageResponse> findWithCommentCount(Pageable pageable);
}
