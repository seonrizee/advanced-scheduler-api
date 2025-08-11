package io.github.seonrizee.scheduler.controller;

import io.github.seonrizee.scheduler.auth.SessionUserId;
import io.github.seonrizee.scheduler.dto.ApiResponse;
import io.github.seonrizee.scheduler.dto.request.CommentCreateRequest;
import io.github.seonrizee.scheduler.dto.request.CommentUpdateRequest;
import io.github.seonrizee.scheduler.dto.response.CommentDetailResponse;
import io.github.seonrizee.scheduler.dto.response.CommentListResponse;
import io.github.seonrizee.scheduler.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

    @GetMapping("/schedules/{scheduleId}/comments")
    public ApiResponse<CommentListResponse> getCommentsWithSchedule(@PathVariable Long scheduleId) {

        CommentListResponse responseDto = commentService.getCommentsWithSchedule(scheduleId);
        return ApiResponse.ok(responseDto);
    }

    @GetMapping("/comments/{commentId}")
    public ApiResponse<CommentDetailResponse> getComment(@PathVariable Long commentId) {
        CommentDetailResponse responseDto = commentService.getComment(commentId);
        return ApiResponse.ok(responseDto);
    }

    @PatchMapping("/comments/{commentId}")
    public ApiResponse<CommentDetailResponse> updateComment(@PathVariable Long commentId,
                                                            @RequestBody CommentUpdateRequest requestDto) {
        CommentDetailResponse responseDto = commentService.updateComment(commentId, requestDto);
        return ApiResponse.ok(responseDto);
    }

    @DeleteMapping("/comments/{commentId}")
    public ApiResponse<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ApiResponse.ok();
    }
}
