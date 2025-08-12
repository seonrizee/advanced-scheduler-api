package io.github.seonrizee.scheduler.service;

import io.github.seonrizee.scheduler.common.code.ErrorCode;
import io.github.seonrizee.scheduler.common.exception.CustomBusinessException;
import io.github.seonrizee.scheduler.dto.response.ScheduleDetailResponse;
import io.github.seonrizee.scheduler.dto.response.ScheduleListResponse;
import io.github.seonrizee.scheduler.entity.Schedule;
import io.github.seonrizee.scheduler.mapper.ScheduleMapper;
import io.github.seonrizee.scheduler.repository.ScheduleRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleFinder {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;

    public ScheduleDetailResponse findScheduleById(Long scheduleId) {
        Schedule schedule = findScheduleByIdOrThrow(scheduleId);
        return scheduleMapper.toDto(schedule);
    }

    public ScheduleListResponse getSchedules() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return scheduleMapper.toDto(schedules);
    }

    public Schedule findScheduleByIdOrThrow(Long scheduleId) {
        return scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new CustomBusinessException(ErrorCode.SCHEDULE_NOT_FOUND));
    }
}
