package io.github.seonrizee.scheduler.service;

import io.github.seonrizee.scheduler.dto.request.ScheduleCreateRequest;
import io.github.seonrizee.scheduler.dto.request.ScheduleUpdateRequest;
import io.github.seonrizee.scheduler.dto.response.ScheduleDetailResponse;
import io.github.seonrizee.scheduler.entity.User;

public interface ScheduleService {

    ScheduleDetailResponse createSchedule(ScheduleCreateRequest requestDto, User user);
    
    ScheduleDetailResponse updateSchedule(Long scheduleId, ScheduleUpdateRequest requestDto, User user);

    void deleteSchedule(Long scheduleId, User user);
}
