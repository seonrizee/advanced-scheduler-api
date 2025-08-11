package io.github.seonrizee.scheduler.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleUpdateRequest {

    @NotBlank(message = "제목은 비어있을 수 없습니다.")
    @Size(max = 50, message = "제목은 최대 50자까지 입력할 수 있습니다.")
    private final String summary;

    @NotBlank(message = "설명은 비어있을 수 없습니다.")
    private final String description;
}
