package io.github.seonrizee.scheduler.domain.schedule.controller;

import io.github.seonrizee.scheduler.domain.schedule.dto.request.ScheduleCreateRequest;
import io.github.seonrizee.scheduler.domain.schedule.dto.request.ScheduleUpdateRequest;
import io.github.seonrizee.scheduler.domain.schedule.dto.response.ScheduleDetailResponse;
import io.github.seonrizee.scheduler.domain.schedule.dto.response.SchedulePageResponse;
import io.github.seonrizee.scheduler.domain.schedule.service.ScheduleQueryService;
import io.github.seonrizee.scheduler.domain.schedule.service.ScheduleService;
import io.github.seonrizee.scheduler.domain.user.entity.User;
import io.github.seonrizee.scheduler.global.annotation.LoginUser;
import io.github.seonrizee.scheduler.global.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    private final ScheduleQueryService scheduleQueryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ScheduleDetailResponse> createSchedule(
            @RequestBody @Valid ScheduleCreateRequest requestDto, @LoginUser User user) {

        ScheduleDetailResponse responseDto = scheduleService.createSchedule(requestDto, user);
        return ApiResponse.created(responseDto);
    }

    @GetMapping("/{scheduleId}")
    public ApiResponse<ScheduleDetailResponse> getSchedule(@PathVariable Long scheduleId) {

        ScheduleDetailResponse responseDto = scheduleQueryService.getSchedule(scheduleId);
        return ApiResponse.ok(responseDto);
    }

    @GetMapping
    public ApiResponse<Page<SchedulePageResponse>> getSchedules(
            @PageableDefault(size = 10, sort = "updatedAt", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<SchedulePageResponse> responseDto = scheduleQueryService.getSchedules(pageable);
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
