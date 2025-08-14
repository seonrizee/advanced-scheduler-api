package io.github.seonrizee.scheduler.domain.schedule.mapper;

import io.github.seonrizee.scheduler.domain.comment.mapper.CommentMapper;
import io.github.seonrizee.scheduler.domain.schedule.dto.request.ScheduleCreateRequest;
import io.github.seonrizee.scheduler.domain.schedule.dto.response.ScheduleDetailResponse;
import io.github.seonrizee.scheduler.domain.schedule.entity.Schedule;
import io.github.seonrizee.scheduler.domain.user.entity.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Schedule 관련 DTO와 Entity 간의 변환을 담당하는 매퍼.
 */
@Component
@RequiredArgsConstructor
public class ScheduleMapper {

    private final CommentMapper commentMapper;

    /**
     * ScheduleCreateRequest DTO를 Schedule 엔티티로 변환합니다.
     * @param requestDto    일정 생성 요청 DTO
     * @param user          일정 생성 사용자
     * @return              Schedule 엔티티
     */
    public Schedule toEntity(ScheduleCreateRequest requestDto, User user) {
        return Schedule.builder()
                .summary(requestDto.getSummary())
                .description(requestDto.getDescription())
                .user(user)
                .build();
    }

    /**
     * Schedule 엔티티를 ScheduleDetailResponse DTO로 변환합니다.
     * @param schedule  Schedule 엔티티
     * @return          ScheduleDetailResponse DTO
     */
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

    /**
     * Schedule 엔티티 리스트를 ScheduleDetailResponse DTO 리스트로 변환합니다.
     * @param schedules Schedule 엔티티 리스트
     * @return          ScheduleDetailResponse DTO 리스트
     */
    public List<ScheduleDetailResponse> toDto(List<Schedule> schedules) {
        return schedules.stream()
                .map(this::toDto)
                .toList();
    }
}
