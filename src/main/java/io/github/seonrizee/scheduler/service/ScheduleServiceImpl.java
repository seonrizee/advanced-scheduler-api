package io.github.seonrizee.scheduler.service;

import io.github.seonrizee.scheduler.common.code.ErrorCode;
import io.github.seonrizee.scheduler.dto.request.ScheduleCreateRequest;
import io.github.seonrizee.scheduler.dto.response.ScheduleDetailResponse;
import io.github.seonrizee.scheduler.entity.Schedule;
import io.github.seonrizee.scheduler.exception.CustomBusinessException;
import io.github.seonrizee.scheduler.mapper.ScheduleMapper;
import io.github.seonrizee.scheduler.repository.ScheduleRepository;
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

        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new CustomBusinessException(ErrorCode.SCHEDULE_NOT_FOUND));

        return scheduleMapper.toDto(schedule);
    }
}
