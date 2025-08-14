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

/**
 * 댓글 조회 관련 서비스를 제공합니다.
 */
@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentQueryService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    /**
     * 특정 댓글의 상세 정보를 조회합니다.
     * @param commentId 조회할 댓글의 ID
     * @return 댓글 상세 정보
     */
    public CommentDetailResponse getComment(Long commentId) {

        Comment savedComment = findCommentOrElseThrow(commentId);
        return commentMapper.toDto(savedComment);
    }

    /**
     * 특정 일정에 달린 모든 댓글 목록을 조회합니다.
     * @param scheduleId 댓글을 조회할 일정의 ID
     * @return 댓글 상세 정보 목록
     */
    public List<CommentDetailResponse> getCommentsWithSchedule(Long scheduleId) {

        List<Comment> commentsByScheduleId = commentRepository.findAllWithUserByScheduleId(scheduleId);
        return commentMapper.toDto(commentsByScheduleId);
    }

    /**
     * ID로 댓글을 찾고, 없으면 예외를 발생시킵니다.
     * @param commentId 찾을 댓글의 ID
     * @return 찾아낸 댓글 엔티티
     * @throws CustomBusinessException 댓글을 찾지 못한 경우
     */
    public Comment findCommentOrElseThrow(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomBusinessException(ErrorCode.COMMENT_NOT_FOUND));
    }
}
