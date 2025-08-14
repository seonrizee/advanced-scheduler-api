package io.github.seonrizee.scheduler.domain.schedule.service;

import io.github.seonrizee.scheduler.domain.schedule.dto.request.ScheduleCreateRequest;
import io.github.seonrizee.scheduler.domain.schedule.dto.request.ScheduleUpdateRequest;
import io.github.seonrizee.scheduler.domain.schedule.dto.response.ScheduleDetailResponse;
import io.github.seonrizee.scheduler.domain.user.entity.User;

/**
 * 일정 관련 비즈니스 로직을 처리하는 서비스 인터페이스.
 */
public interface ScheduleService {

    /**
     * 새로운 일정을 생성합니다.
     * @param requestDto    일정 생성 요청 데이터
     * @param user          현재 로그인한 사용자
     * @return              생성된 일정의 상세 정보
     */
    ScheduleDetailResponse createSchedule(ScheduleCreateRequest requestDto, User user);

    /**
     * 일정을 수정합니다.
     * @param scheduleId    수정할 일정의 ID
     * @param requestDto    일정 수정 요청 데이터
     * @param user          현재 로그인한 사용자
     * @return              수정된 일정의 상세 정보
     */
    ScheduleDetailResponse updateSchedule(Long scheduleId, ScheduleUpdateRequest requestDto, User user);

    /**
     * 일정을 삭제합니다.
     * @param scheduleId    삭제할 일정의 ID
     * @param user          현재 로그인한 사용자
     */
    void deleteSchedule(Long scheduleId, User user);
}
