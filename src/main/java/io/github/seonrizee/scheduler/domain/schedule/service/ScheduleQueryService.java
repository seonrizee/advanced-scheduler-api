package io.github.seonrizee.scheduler.domain.schedule.service;

import io.github.seonrizee.scheduler.domain.schedule.dto.response.ScheduleDetailResponse;
import io.github.seonrizee.scheduler.domain.schedule.dto.response.SchedulePageResponse;
import io.github.seonrizee.scheduler.domain.schedule.entity.Schedule;
import io.github.seonrizee.scheduler.domain.schedule.mapper.ScheduleMapper;
import io.github.seonrizee.scheduler.domain.schedule.repository.ScheduleRepository;
import io.github.seonrizee.scheduler.global.code.ErrorCode;
import io.github.seonrizee.scheduler.global.exception.CustomBusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 일정 조회 관련 서비스를 제공합니다.
 */
@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleQueryService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;

    /**
     * 특정 일정의 상세 정보를 조회합니다.
     * @param scheduleId 조회할 일정의 ID
     * @return 일정 상세 정보
     */
    public ScheduleDetailResponse getSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findWithCommentsById(scheduleId)
                .orElseThrow(() -> new CustomBusinessException(ErrorCode.SCHEDULE_NOT_FOUND));
        return scheduleMapper.toDto(schedule);
    }

    /**
     * 모든 일정 목록을 페이지네이션하여 조회합니다.
     * @param pageable 페이지 정보
     * @return 일정 목록 페이지
     */
    public Page<SchedulePageResponse> getSchedules(Pageable pageable) {

        return scheduleRepository.findWithCommentCount(pageable);
    }

    /**
     * ID로 일정을 찾고, 없으면 예외를 발생시킵니다.
     * @param scheduleId 찾을 일정의 ID
     * @return 찾아낸 일정 엔티티
     * @throws CustomBusinessException 일정을 찾지 못한 경우
     */
    public Schedule findScheduleByIdOrThrow(Long scheduleId) {
        return scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new CustomBusinessException(ErrorCode.SCHEDULE_NOT_FOUND));
    }
}
