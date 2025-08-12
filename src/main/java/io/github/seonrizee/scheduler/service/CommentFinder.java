package io.github.seonrizee.scheduler.service;

import io.github.seonrizee.scheduler.common.code.ErrorCode;
import io.github.seonrizee.scheduler.common.exception.CustomBusinessException;
import io.github.seonrizee.scheduler.dto.response.CommentDetailResponse;
import io.github.seonrizee.scheduler.dto.response.CommentListResponse;
import io.github.seonrizee.scheduler.entity.Comment;
import io.github.seonrizee.scheduler.mapper.CommentMapper;
import io.github.seonrizee.scheduler.repository.CommentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentFinder {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public CommentDetailResponse getComment(Long commentId) {

        Comment savedComment = findCommentOrElseThrow(commentId);
        return commentMapper.toDto(savedComment);
    }

    public CommentListResponse getCommentsWithSchedule(Long scheduleId) {

        List<Comment> commentsByScheduleId = commentRepository.findAllByScheduleId(scheduleId);
        return commentMapper.toDto(commentsByScheduleId);
    }

    public Comment findCommentOrElseThrow(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomBusinessException(ErrorCode.COMMENT_NOT_FOUND));
    }
}
