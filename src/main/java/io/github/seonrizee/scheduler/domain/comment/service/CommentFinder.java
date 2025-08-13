package io.github.seonrizee.scheduler.domain.comment.service;

import io.github.seonrizee.scheduler.domain.comment.dto.response.CommentDetailResponse;
import io.github.seonrizee.scheduler.domain.comment.entity.Comment;
import io.github.seonrizee.scheduler.domain.comment.mapper.CommentMapper;
import io.github.seonrizee.scheduler.domain.comment.repository.CommentRepository;
import io.github.seonrizee.scheduler.global.code.ErrorCode;
import io.github.seonrizee.scheduler.global.exception.CustomBusinessException;
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

    public List<CommentDetailResponse> getCommentsWithSchedule(Long scheduleId) {

        List<Comment> commentsByScheduleId = commentRepository.findAllWithUserByScheduleId(scheduleId);
        return commentMapper.toDto(commentsByScheduleId);
    }

    public Comment findCommentOrElseThrow(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomBusinessException(ErrorCode.COMMENT_NOT_FOUND));
    }
}
