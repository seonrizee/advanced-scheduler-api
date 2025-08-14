package io.github.seonrizee.scheduler.domain.schedule.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 일정 수정을 위한 요청 DTO.
 */
@Getter
@AllArgsConstructor
public class ScheduleUpdateRequest {

    /**
     * 수정할 일정 요약.
     * 최대 50자까지 입력할 수 있습니다.
     */
    @NotBlank(message = "제목은 비어있을 수 없습니다.")
    @Size(max = 50, message = "제목은 최대 50자까지 입력할 수 있습니다.")
    private final String summary;

    /**
     * 수정할 일정 상세 설명.
     */
    @NotBlank(message = "설명은 비어있을 수 없습니다.")
    private final String description;
}
