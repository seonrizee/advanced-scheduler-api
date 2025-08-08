package io.github.seonrizee.scheduler.service;

import io.github.seonrizee.scheduler.dto.request.ScheduleCreateRequest;
import io.github.seonrizee.scheduler.dto.response.ScheduleDetailResponse;

public interface ScheduleService {

    ScheduleDetailResponse createSchedule(ScheduleCreateRequest requestDto);

    ScheduleDetailResponse findScheduleById(Long scheduleId);
}
