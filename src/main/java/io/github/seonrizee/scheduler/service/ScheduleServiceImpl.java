package io.github.seonrizee.scheduler.service;

import io.github.seonrizee.scheduler.dto.request.ScheduleCreateRequest;
import io.github.seonrizee.scheduler.dto.request.ScheduleUpdateRequest;
import io.github.seonrizee.scheduler.dto.response.ScheduleDetailResponse;
import io.github.seonrizee.scheduler.entity.Schedule;
import io.github.seonrizee.scheduler.entity.User;
import io.github.seonrizee.scheduler.mapper.ScheduleMapper;
import io.github.seonrizee.scheduler.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleFinder scheduleFinder;
    private final ScheduleMapper scheduleMapper;

    @Override
    public ScheduleDetailResponse createSchedule(ScheduleCreateRequest requestDto, User user) {

        Schedule savedSchedule = scheduleRepository.save(scheduleMapper.toEntity(requestDto, user));
        return scheduleMapper.toDto(savedSchedule);
    }


    @Override
    public ScheduleDetailResponse updateSchedule(Long scheduleId, ScheduleUpdateRequest requestDto, User user) {

        // TODO 세부 인가 - 내가 작성한건지 확인 필요
        Schedule existingSchedule = scheduleFinder.findScheduleByIdOrThrow(scheduleId);
        existingSchedule.updateDetail(requestDto.getSummary(), requestDto.getDescription());
        return scheduleMapper.toDto(existingSchedule);
    }

    @Override
    public void deleteSchedule(Long scheduleId, User user) {

        // TODO 세부 인가 - 내가 작성한건지 확인 필요
        Schedule existingSchedule = scheduleFinder.findScheduleByIdOrThrow(scheduleId);
        scheduleRepository.delete(existingSchedule);
    }
}
