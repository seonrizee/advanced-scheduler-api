package io.github.seonrizee.scheduler.controller;

import io.github.seonrizee.scheduler.dto.ApiResponse;
import io.github.seonrizee.scheduler.dto.request.ScheduleCreateRequest;
import io.github.seonrizee.scheduler.dto.request.ScheduleUpdateRequest;
import io.github.seonrizee.scheduler.dto.response.ScheduleDetailResponse;
import io.github.seonrizee.scheduler.dto.response.ScheduleListResponse;
import io.github.seonrizee.scheduler.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{scheduleId}")
    public ResponseEntity<ApiResponse<ScheduleDetailResponse>> getSchedule(@PathVariable Long scheduleId) {

        ScheduleDetailResponse responseDto = scheduleService.findScheduleById(scheduleId);

        return ApiResponse.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<ScheduleListResponse>> getSchedules() {

        ScheduleListResponse responseDto = scheduleService.findAllSchedules();
        return ApiResponse.ok(responseDto);
    }

    @PatchMapping("/{scheduleId}")
    public ResponseEntity<ApiResponse<ScheduleDetailResponse>> updateSchedule(
            @PathVariable Long scheduleId,
            @RequestBody ScheduleUpdateRequest requestDto) {

        ScheduleDetailResponse responseDto = scheduleService.updateSchedule(scheduleId, requestDto);
        return ApiResponse.ok(responseDto);
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<ApiResponse<Void>> deleteSchedule(@PathVariable Long scheduleId) {

        scheduleService.deleteSchedule(scheduleId);
        return ApiResponse.ok();
    }
}
