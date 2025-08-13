package io.github.seonrizee.scheduler.domain.schedule.dto.response;

public interface SchedulePageResponse {
    Long getId();

    String getSummary();

    String getDescription();

    Long getCommentCount();

    Long getUserId();

    String getUsername();

    java.time.LocalDateTime getCreatedAt();

    java.time.LocalDateTime getUpdatedAt();
}
