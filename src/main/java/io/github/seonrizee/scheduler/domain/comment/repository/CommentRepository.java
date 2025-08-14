package io.github.seonrizee.scheduler.domain.comment.repository;

import io.github.seonrizee.scheduler.domain.comment.entity.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Comment 엔티티에 대한 데이터베이스 액세스를 제공하는 리포지토리.
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * 특정 일정 ID에 해당하는 모든 댓글을 작성자 정보와 함께 조회합니다.
     * @param scheduleId 조회할 일정의 ID
     * @return 댓글 목록
     */
    @Query("SELECT c FROM Comment c JOIN FETCH c.user WHERE c.schedule.id = :scheduleId")
    List<Comment> findAllWithUserByScheduleId(@Param("scheduleId") Long scheduleId);
}
