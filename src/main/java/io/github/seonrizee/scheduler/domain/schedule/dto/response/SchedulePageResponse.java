package io.github.seonrizee.scheduler.domain.schedule.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SchedulePageResponse {

    private final Long id;
    private final String summary;
    private final String description;
    private final Long commentCount;
    private final Long userId;
    private final String username;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}
