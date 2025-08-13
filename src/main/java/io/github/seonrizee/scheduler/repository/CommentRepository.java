package io.github.seonrizee.scheduler.repository;

import io.github.seonrizee.scheduler.entity.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT c FROM Comment c JOIN FETCH c.user WHERE c.schedule.id = :scheduleId")
    List<Comment> findAllWithUserByScheduleId(@Param("scheduleId") Long scheduleId);
}
