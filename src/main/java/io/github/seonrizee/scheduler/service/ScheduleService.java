package io.github.seonrizee.scheduler.service;

import io.github.seonrizee.scheduler.dto.request.ScheduleCreateRequest;
import io.github.seonrizee.scheduler.dto.request.ScheduleUpdateRequest;
import io.github.seonrizee.scheduler.dto.response.ScheduleDetailResponse;
import io.github.seonrizee.scheduler.dto.response.ScheduleListResponse;

public interface ScheduleService {

    ScheduleDetailResponse createSchedule(ScheduleCreateRequest requestDto);

    ScheduleDetailResponse findScheduleById(Long scheduleId);

    ScheduleListResponse findAllSchedules();

    ScheduleDetailResponse updateSchedule(Long scheduleId, ScheduleUpdateRequest requestDto);
}
