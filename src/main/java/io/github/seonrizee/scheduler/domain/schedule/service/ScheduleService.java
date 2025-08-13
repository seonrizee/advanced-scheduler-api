package io.github.seonrizee.scheduler.domain.schedule.service;

import io.github.seonrizee.scheduler.domain.schedule.dto.request.ScheduleCreateRequest;
import io.github.seonrizee.scheduler.domain.schedule.dto.request.ScheduleUpdateRequest;
import io.github.seonrizee.scheduler.domain.schedule.dto.response.ScheduleDetailResponse;
import io.github.seonrizee.scheduler.domain.user.entity.User;

public interface ScheduleService {

    ScheduleDetailResponse createSchedule(ScheduleCreateRequest requestDto, User user);

    ScheduleDetailResponse updateSchedule(Long scheduleId, ScheduleUpdateRequest requestDto, User user);

    void deleteSchedule(Long scheduleId, User user);
}
