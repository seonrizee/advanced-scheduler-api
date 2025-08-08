package io.github.seonrizee.scheduler.mapper;

import io.github.seonrizee.scheduler.dto.request.ScheduleCreateRequest;
import io.github.seonrizee.scheduler.dto.response.ScheduleDetailResponse;
import io.github.seonrizee.scheduler.dto.response.ScheduleListResponse;
import io.github.seonrizee.scheduler.entity.Schedule;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ScheduleMapper {
    public Schedule toEntity(ScheduleCreateRequest requestDto) {
        return Schedule.builder()
                .summary(requestDto.getSummary())
                .description(requestDto.getDescription())
                .username(requestDto.getUsername())
                .build();
    }

    public ScheduleDetailResponse toDto(Schedule schedule) {
        return new ScheduleDetailResponse(
                schedule.getId(),
                schedule.getSummary(),
                schedule.getDescription(),
                schedule.getUsername(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt());
    }

    public ScheduleListResponse toDto(List<Schedule> schedules) {
        return new ScheduleListResponse(
                schedules.stream()
                        .map(this::toDto)
                        .toList()
        );
    }
}
