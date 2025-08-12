package io.github.seonrizee.scheduler.service;

import io.github.seonrizee.scheduler.dto.request.CommentCreateRequest;
import io.github.seonrizee.scheduler.dto.request.CommentUpdateRequest;
import io.github.seonrizee.scheduler.dto.response.CommentDetailResponse;
import io.github.seonrizee.scheduler.dto.response.CommentListResponse;

public interface CommentService {

    CommentDetailResponse createComment(Long scheduleId, CommentCreateRequest requestDto, Long userId);

    CommentDetailResponse getComment(Long commentId);

    CommentListResponse getCommentsWithSchedule(Long scheduleId);

    CommentDetailResponse updateComment(Long commentId, CommentUpdateRequest requestDto, Long userId);

    void deleteComment(Long commentId, Long userId);

}
