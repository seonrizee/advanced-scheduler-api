package io.github.seonrizee.scheduler.controller;

import io.github.seonrizee.scheduler.common.annotation.LoginUser;
import io.github.seonrizee.scheduler.dto.ApiResponse;
import io.github.seonrizee.scheduler.dto.request.ScheduleCreateRequest;
import io.github.seonrizee.scheduler.dto.request.ScheduleUpdateRequest;
import io.github.seonrizee.scheduler.dto.response.ScheduleDetailResponse;
import io.github.seonrizee.scheduler.entity.User;
import io.github.seonrizee.scheduler.service.ScheduleFinder;
import io.github.seonrizee.scheduler.service.ScheduleService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final ScheduleFinder scheduleFinder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ScheduleDetailResponse> createSchedule(
            @RequestBody @Valid ScheduleCreateRequest requestDto, @LoginUser User user) {

        ScheduleDetailResponse responseDto = scheduleService.createSchedule(requestDto, user);
        return ApiResponse.created(responseDto);
    }

    @GetMapping("/{scheduleId}")
    public ApiResponse<ScheduleDetailResponse> getSchedule(@PathVariable Long scheduleId) {

        ScheduleDetailResponse responseDto = scheduleFinder.getSchedule(scheduleId);
        return ApiResponse.ok(responseDto);
    }

    @GetMapping
    public ApiResponse<List<ScheduleDetailResponse>> getSchedules() {

        List<ScheduleDetailResponse> responseDto = scheduleFinder.getSchedules();
        return ApiResponse.ok(responseDto);
    }

    @PatchMapping("/{scheduleId}")
    public ApiResponse<ScheduleDetailResponse> updateSchedule(
            @PathVariable Long scheduleId,
            @RequestBody @Valid ScheduleUpdateRequest requestDto,
            @LoginUser User user) {

        ScheduleDetailResponse responseDto = scheduleService.updateSchedule(scheduleId, requestDto, user);
        return ApiResponse.ok(responseDto);
    }

    @DeleteMapping("/{scheduleId}")
    public ApiResponse<Void> deleteSchedule(@PathVariable Long scheduleId, @LoginUser User user) {

        scheduleService.deleteSchedule(scheduleId, user);
        return ApiResponse.ok();
    }
}
