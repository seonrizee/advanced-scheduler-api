package io.github.seonrizee.scheduler.controller;

import io.github.seonrizee.scheduler.dto.ApiResponse;
import io.github.seonrizee.scheduler.dto.request.ScheduleCreateRequest;
import io.github.seonrizee.scheduler.dto.response.ScheduleDetailResponse;
import io.github.seonrizee.scheduler.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ApiResponse<ScheduleDetailResponse>> createSchedule(
            @RequestBody ScheduleCreateRequest requestDto) {

        ScheduleDetailResponse responseDto = scheduleService.createSchedule(requestDto);

        return ApiResponse.created(responseDto);
    }
}
