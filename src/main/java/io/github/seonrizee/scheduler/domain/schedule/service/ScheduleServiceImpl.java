package io.github.seonrizee.scheduler.domain.schedule.service;

import io.github.seonrizee.scheduler.domain.schedule.dto.request.ScheduleCreateRequest;
import io.github.seonrizee.scheduler.domain.schedule.dto.request.ScheduleUpdateRequest;
import io.github.seonrizee.scheduler.domain.schedule.dto.response.ScheduleDetailResponse;
import io.github.seonrizee.scheduler.domain.schedule.entity.Schedule;
import io.github.seonrizee.scheduler.domain.schedule.mapper.ScheduleMapper;
import io.github.seonrizee.scheduler.domain.schedule.repository.ScheduleRepository;
import io.github.seonrizee.scheduler.domain.user.entity.User;
import io.github.seonrizee.scheduler.global.security.service.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleQueryService scheduleQueryService;
    private final ScheduleMapper scheduleMapper;
    private final AuthorizationService authorizationService;

    @Override
    public ScheduleDetailResponse createSchedule(ScheduleCreateRequest requestDto, User user) {

        Schedule savedSchedule = scheduleRepository.save(scheduleMapper.toEntity(requestDto, user));
        return scheduleMapper.toDto(savedSchedule);
    }


    @Override
    public ScheduleDetailResponse updateSchedule(Long scheduleId, ScheduleUpdateRequest requestDto, User user) {

        Schedule savedSchedule = scheduleQueryService.findScheduleByIdOrThrow(scheduleId);
        authorizationService.validateOwnership(savedSchedule, user, s -> s.getUser().getId());
        savedSchedule.updateDetail(requestDto.getSummary(), requestDto.getDescription());
        return scheduleMapper.toDto(savedSchedule);
    }

    @Override
    public void deleteSchedule(Long scheduleId, User user) {

        Schedule savedSchedule = scheduleQueryService.findScheduleByIdOrThrow(scheduleId);
        authorizationService.validateOwnership(savedSchedule, user, s -> s.getUser().getId());
        scheduleRepository.delete(savedSchedule);
    }
}
