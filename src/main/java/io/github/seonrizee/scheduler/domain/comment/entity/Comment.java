package io.github.seonrizee.scheduler.domain.comment.entity;

import io.github.seonrizee.scheduler.domain.schedule.entity.Schedule;
import io.github.seonrizee.scheduler.domain.user.entity.User;
import io.github.seonrizee.scheduler.global.entity.BaseDateTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 댓글 정보를 담는 엔티티.
 */
@Entity
@Getter
@NoArgsConstructor
public class Comment extends BaseDateTimeEntity {

    /**
     * 댓글 ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    /**
     * 댓글 내용.
     */
    @Column(nullable = false)
    private String content;

    /**
     * 댓글 작성자.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * 댓글이 달린 일정.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    /**
     * Comment 객체를 생성합니다.
     * @param content   댓글 내용
     * @param user      작성자
     * @param schedule  일정
     */
    @Builder
    public Comment(String content, User user, Schedule schedule) {
        this.content = content;
        this.user = user;
        this.schedule = schedule;
    }

    /**
     * 댓글 내용을 수정합니다.
     * @param content 새로운 댓글 내용
     */
    public void updateContent(String content) {
        this.content = content;
    }

    /**
     * 댓글이 달린 일정을 수정합니다.
     * @param schedule 새로운 일정
     */
    public void updateSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
