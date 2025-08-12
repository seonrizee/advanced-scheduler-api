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
    private final AuthorizationService authorizationService;

    @Override
    public ScheduleDetailResponse createSchedule(ScheduleCreateRequest requestDto, User user) {

        Schedule savedSchedule = scheduleRepository.save(scheduleMapper.toEntity(requestDto, user));
        return scheduleMapper.toDto(savedSchedule);
    }


    @Override
    public ScheduleDetailResponse updateSchedule(Long scheduleId, ScheduleUpdateRequest requestDto, User user) {

        Schedule savedSchedule = scheduleFinder.findScheduleByIdOrThrow(scheduleId);
        authorizationService.validateOwnership(savedSchedule, user);
        savedSchedule.updateDetail(requestDto.getSummary(), requestDto.getDescription());
        return scheduleMapper.toDto(savedSchedule);
    }

    @Override
    public void deleteSchedule(Long scheduleId, User user) {

        Schedule savedSchedule = scheduleFinder.findScheduleByIdOrThrow(scheduleId);
        authorizationService.validateOwnership(savedSchedule, user);
        scheduleRepository.delete(savedSchedule);
    }
}
