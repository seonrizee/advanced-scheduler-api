package io.github.seonrizee.scheduler.controller;

import io.github.seonrizee.scheduler.auth.SessionUserId;
import io.github.seonrizee.scheduler.dto.ApiResponse;
import io.github.seonrizee.scheduler.dto.request.CommentCreateRequest;
import io.github.seonrizee.scheduler.dto.response.CommentDetailResponse;
import io.github.seonrizee.scheduler.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/schedules/{scheduleId}/comments")
    public ApiResponse<CommentDetailResponse> createComment(@PathVariable Long scheduleId,
                                                            @RequestBody CommentCreateRequest requestDto,
                                                            @SessionUserId Long userId
    ) {

        CommentDetailResponse responseDto = commentService.createComment(scheduleId, userId, requestDto);
        return ApiResponse.created(responseDto);
    }
}
