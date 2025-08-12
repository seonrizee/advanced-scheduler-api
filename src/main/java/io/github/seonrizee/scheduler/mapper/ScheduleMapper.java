package io.github.seonrizee.scheduler.mapper;

import io.github.seonrizee.scheduler.dto.request.ScheduleCreateRequest;
import io.github.seonrizee.scheduler.dto.response.ScheduleDetailResponse;
import io.github.seonrizee.scheduler.dto.response.SchedulePageResponse;
import io.github.seonrizee.scheduler.entity.Schedule;
import io.github.seonrizee.scheduler.entity.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduleMapper {

    private final CommentMapper commentMapper;

    public Schedule toEntity(ScheduleCreateRequest requestDto, User user) {
        return Schedule.builder()
                .summary(requestDto.getSummary())
                .description(requestDto.getDescription())
                .user(user)
                .build();
    }

    public ScheduleDetailResponse toDto(Schedule schedule) {
        return new ScheduleDetailResponse(
                schedule.getId(),
                schedule.getSummary(),
                schedule.getDescription(),
                schedule.getUser().getId(),
                schedule.getUser().getUsername(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt(),
                schedule.getComments().stream().map(commentMapper::toDto).toList()
        );
    }

    public SchedulePageResponse toPageDto(Schedule schedule) {
        return new SchedulePageResponse(
                schedule.getId(),
                schedule.getSummary(),
                schedule.getDescription(),
                schedule.getComments().size(),
                schedule.getUser().getId(),
                schedule.getUser().getUsername(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
    }

    public List<ScheduleDetailResponse> toDto(List<Schedule> schedules) {
        return schedules.stream()
                .map(this::toDto)
                .toList();
    }
}
