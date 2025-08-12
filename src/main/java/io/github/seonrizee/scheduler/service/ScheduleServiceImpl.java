package io.github.seonrizee.scheduler.service;

import io.github.seonrizee.scheduler.dto.request.ScheduleCreateRequest;
import io.github.seonrizee.scheduler.dto.request.ScheduleUpdateRequest;
import io.github.seonrizee.scheduler.dto.response.ScheduleDetailResponse;
import io.github.seonrizee.scheduler.dto.response.ScheduleListResponse;
import io.github.seonrizee.scheduler.entity.Schedule;
import io.github.seonrizee.scheduler.entity.User;
import io.github.seonrizee.scheduler.mapper.ScheduleMapper;
import io.github.seonrizee.scheduler.repository.ScheduleRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;
    private final UserFinder userFinder;

    @Override
    @Transactional
    public ScheduleDetailResponse createSchedule(ScheduleCreateRequest requestDto, User user) {

        Schedule savedSchedule = scheduleRepository.save(scheduleMapper.toEntity(requestDto, user));
        return scheduleMapper.toDto(savedSchedule);
    }

    @Override
    public ScheduleDetailResponse findScheduleById(Long scheduleId) {
        Schedule schedule = scheduleRepository.findScheduleByIdOrThrow(scheduleId);
        return scheduleMapper.toDto(schedule);
    }

    @Override
    public ScheduleListResponse getSchedules() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return scheduleMapper.toDto(schedules);
    }

    @Override
    @Transactional
    public ScheduleDetailResponse updateSchedule(Long scheduleId, ScheduleUpdateRequest requestDto, User user) {

        // TODO 세부 인가 - 내가 작성한건지 확인 필요
        Schedule existingSchedule = scheduleRepository.findScheduleByIdOrThrow(scheduleId);
        existingSchedule.updateDetail(requestDto.getSummary(), requestDto.getDescription());
        return scheduleMapper.toDto(existingSchedule);
    }

    @Override
    @Transactional
    public void deleteSchedule(Long scheduleId, User user) {

        // TODO 세부 인가 - 내가 작성한건지 확인 필요
        Schedule existingSchedule = scheduleRepository.findScheduleByIdOrThrow(scheduleId);
        scheduleRepository.delete(existingSchedule);
    }
}
