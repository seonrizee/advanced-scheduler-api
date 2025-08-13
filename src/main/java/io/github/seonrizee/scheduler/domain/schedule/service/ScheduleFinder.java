package io.github.seonrizee.scheduler.domain.schedule.service;

import io.github.seonrizee.scheduler.domain.schedule.dto.response.ScheduleDetailResponse;
import io.github.seonrizee.scheduler.domain.schedule.dto.response.SchedulePageResponse;
import io.github.seonrizee.scheduler.domain.schedule.entity.Schedule;
import io.github.seonrizee.scheduler.domain.schedule.mapper.ScheduleMapper;
import io.github.seonrizee.scheduler.domain.schedule.repository.ScheduleRepository;
import io.github.seonrizee.scheduler.global.code.ErrorCode;
import io.github.seonrizee.scheduler.global.exception.CustomBusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleFinder {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;

    public ScheduleDetailResponse getSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findWithCommentsById(scheduleId)
                .orElseThrow(() -> new CustomBusinessException(ErrorCode.SCHEDULE_NOT_FOUND));
        return scheduleMapper.toDto(schedule);
    }

    public Page<SchedulePageResponse> getSchedules(Pageable pageable) {

        return scheduleRepository.findWithCommentCount(pageable);
    }

    public Schedule findScheduleByIdOrThrow(Long scheduleId) {
        return scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new CustomBusinessException(ErrorCode.SCHEDULE_NOT_FOUND));
    }
}
