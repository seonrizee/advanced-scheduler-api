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

/**
 * 일정 관련 API를 제공하는 컨트롤러.
 */
@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final ScheduleQueryService scheduleQueryService;

    /**
     * 새로운 일정을 생성합니다.
     * @param requestDto    일정 생성 요청 데이터
     * @param user          현재 로그인한 사용자
     * @return              생성된 일정의 상세 정보
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ScheduleDetailResponse> createSchedule(
            @RequestBody @Valid ScheduleCreateRequest requestDto, @LoginUser User user) {

        ScheduleDetailResponse responseDto = scheduleService.createSchedule(requestDto, user);
        return ApiResponse.created(responseDto);
    }

    /**
     * 특정 일정의 상세 정보를 조회합니다.
     * @param scheduleId 조회할 일정의 ID
     * @return 일정 상세 정보
     */
    @GetMapping("/{scheduleId}")
    public ApiResponse<ScheduleDetailResponse> getSchedule(@PathVariable Long scheduleId) {

        ScheduleDetailResponse responseDto = scheduleQueryService.getSchedule(scheduleId);
        return ApiResponse.ok(responseDto);
    }

    /**
     * 모든 일정 목록을 페이지네이션하여 조회합니다.
     * @param pageable 페이지 정보
     * @return 일정 목록 페이지
     */
    @GetMapping
    public ApiResponse<Page<SchedulePageResponse>> getSchedules(
            @PageableDefault(size = 10, sort = "updatedAt", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<SchedulePageResponse> responseDto = scheduleQueryService.getSchedules(pageable);
        return ApiResponse.ok(responseDto);
    }

    /**
     * 일정을 수정합니다.
     * @param scheduleId    수정할 일정의 ID
     * @param requestDto    수정할 일정 정보
     * @param user          현재 로그인한 사용자
     * @return              수정된 일정의 상세 정보
     */
    @PatchMapping("/{scheduleId}")
    public ApiResponse<ScheduleDetailResponse> updateSchedule(
            @PathVariable Long scheduleId,
            @RequestBody @Valid ScheduleUpdateRequest requestDto,
            @LoginUser User user) {

        ScheduleDetailResponse responseDto = scheduleService.updateSchedule(scheduleId, requestDto, user);
        return ApiResponse.ok(responseDto);
    }

    /**
     * 일정을 삭제합니다.
     * @param scheduleId    삭제할 일정의 ID
     * @param user          현재 로그인한 사용자
     * @return              성공 응답
     */
    @DeleteMapping("/{scheduleId}")
    public ApiResponse<Void> deleteSchedule(@PathVariable Long scheduleId, @LoginUser User user) {

        scheduleService.deleteSchedule(scheduleId, user);
        return ApiResponse.ok();
    }
}
