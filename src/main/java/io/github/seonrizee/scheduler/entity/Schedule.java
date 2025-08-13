package io.github.seonrizee.scheduler.entity;

import io.github.seonrizee.scheduler.common.entity.BaseDateTimeEntity;
import io.github.seonrizee.scheduler.common.entity.Ownable;
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

@Entity
@Getter
@NoArgsConstructor
public class Schedule extends BaseDateTimeEntity implements Ownable {


    @OneToMany(mappedBy = "schedule", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private final List<Comment> comments = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;

    @Column(nullable = false)
    private String summary;

    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public Schedule(String summary, String description, User user) {
        this.summary = summary;
        this.description = description;
        this.user = user;
    }

    public void updateDetail(String summary, String description) {
        this.summary = summary;
        this.description = description;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.updateSchedule(this);
    }
}
