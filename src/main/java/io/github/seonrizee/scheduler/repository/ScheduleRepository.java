package io.github.seonrizee.scheduler.repository;

import io.github.seonrizee.scheduler.common.code.ErrorCode;
import io.github.seonrizee.scheduler.common.exception.CustomBusinessException;
import io.github.seonrizee.scheduler.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    default Schedule findScheduleByIdOrThrow(Long scheduleId) {
        return findById(scheduleId)
                .orElseThrow(() -> new CustomBusinessException(ErrorCode.SCHEDULE_NOT_FOUND));
    }
}
