package io.github.seonrizee.scheduler.domain.schedule.entity;

import io.github.seonrizee.scheduler.domain.comment.entity.Comment;
import io.github.seonrizee.scheduler.domain.user.entity.User;
import io.github.seonrizee.scheduler.global.entity.BaseDateTimeEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 일정 정보를 담는 엔티티.
 */
@Entity
@Getter
@NoArgsConstructor
public class Schedule extends BaseDateTimeEntity {


    /**
     * 일정에 달린 댓글 목록.
     */
    @OneToMany(mappedBy = "schedule", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private final List<Comment> comments = new ArrayList<>();

    /**
     * 일정 ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;

    /**
     * 일정 요약.
     */
    @Column(nullable = false)
    private String summary;

    /**
     * 일정 상세 설명.
     */
    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    /**
     * 일정을 생성한 사용자.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * Schedule 객체를 생성합니다.
     * @param summary       일정 요약
     * @param description   일정 상세 설명
     * @param user          사용자
     */
    @Builder
    public Schedule(String summary, String description, User user) {
        this.summary = summary;
        this.description = description;
        this.user = user;
    }

    /**
     * 일정의 상세 정보를 수정합니다.
     * @param summary       새로운 일정 요약
     * @param description   새로운 일정 상세 설명
     */
    public void updateDetail(String summary, String description) {
        this.summary = summary;
        this.description = description;
    }

    /**
     * 일정에 댓글을 추가합니다.
     * @param comment 추가할 댓글
     */
    public void addComment(Comment comment) {
        comments.add(comment);
        comment.updateSchedule(this);
    }
}
