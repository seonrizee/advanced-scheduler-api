package io.github.seonrizee.scheduler.service;

import io.github.seonrizee.scheduler.dto.request.CommentCreateRequest;
import io.github.seonrizee.scheduler.dto.request.CommentUpdateRequest;
import io.github.seonrizee.scheduler.dto.response.CommentDetailResponse;
import io.github.seonrizee.scheduler.entity.User;

public interface CommentService {

    CommentDetailResponse createComment(Long scheduleId, CommentCreateRequest requestDto, User user);

    CommentDetailResponse updateComment(Long commentId, CommentUpdateRequest requestDto, User user);

    void deleteComment(Long commentId, User user);

}
