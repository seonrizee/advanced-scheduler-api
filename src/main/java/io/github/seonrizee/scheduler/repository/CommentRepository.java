package io.github.seonrizee.scheduler.repository;

import io.github.seonrizee.scheduler.entity.Comment;
import io.github.seonrizee.scheduler.entity.Schedule;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllBySchedule(Schedule schedule);
}
