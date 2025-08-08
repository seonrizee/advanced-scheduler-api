package io.github.seonrizee.scheduler.service;

import io.github.seonrizee.scheduler.common.code.ErrorCode;
import io.github.seonrizee.scheduler.dto.request.ScheduleCreateRequest;
import io.github.seonrizee.scheduler.dto.request.ScheduleUpdateRequest;
import io.github.seonrizee.scheduler.dto.response.ScheduleDetailResponse;
import io.github.seonrizee.scheduler.dto.response.ScheduleListResponse;
import io.github.seonrizee.scheduler.entity.Schedule;
import io.github.seonrizee.scheduler.exception.CustomBusinessException;
import io.github.seonrizee.scheduler.mapper.ScheduleMapper;
import io.github.seonrizee.scheduler.repository.ScheduleRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;

    @Override
    public ScheduleDetailResponse createSchedule(ScheduleCreateRequest requestDto) {

        Schedule savedSchedule = scheduleRepository.save(scheduleMapper.toEntity(requestDto));

        return scheduleMapper.toDto(savedSchedule);
    }

    @Override
    public ScheduleDetailResponse findScheduleById(Long scheduleId) {

        Schedule schedule = findScheduleByIdOrThrow(scheduleId);

        return scheduleMapper.toDto(schedule);
    }

    @Override
    public ScheduleListResponse findAllSchedules() {

        List<Schedule> schedules = scheduleRepository.findAll();
        return scheduleMapper.toDto(schedules);
    }

    @Override
    @Transactional
    public ScheduleDetailResponse updateSchedule(Long scheduleId, ScheduleUpdateRequest requestDto) {

        Schedule existingSchedule = findScheduleByIdOrThrow(scheduleId);
        existingSchedule.updateDetail(requestDto.getSummary(), requestDto.getDescription());

        return scheduleMapper.toDto(existingSchedule);
    }

    private Schedule findScheduleByIdOrThrow(Long scheduleId) {
        return scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new CustomBusinessException(ErrorCode.SCHEDULE_NOT_FOUND));
    }
}
