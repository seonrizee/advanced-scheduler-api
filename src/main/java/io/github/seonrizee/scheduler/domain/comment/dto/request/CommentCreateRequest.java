package io.github.seonrizee.scheduler.domain.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentCreateRequest {

    @NotBlank(message = "댓글 내용은 비어있을 수 없습니다.")
    @Size(max = 255, message = "댓글 내용은 최대 255자까지 입력할 수 있습니다.")
    public final String content;
}
