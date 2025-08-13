package io.github.seonrizee.scheduler.domain.comment.service;

import io.github.seonrizee.scheduler.domain.comment.dto.request.CommentCreateRequest;
import io.github.seonrizee.scheduler.domain.comment.dto.request.CommentUpdateRequest;
import io.github.seonrizee.scheduler.domain.comment.dto.response.CommentDetailResponse;
import io.github.seonrizee.scheduler.domain.user.entity.User;

public interface CommentService {

    CommentDetailResponse createComment(Long scheduleId, CommentCreateRequest requestDto, User user);

    CommentDetailResponse updateComment(Long commentId, CommentUpdateRequest requestDto, User user);

    void deleteComment(Long commentId, User user);

}
