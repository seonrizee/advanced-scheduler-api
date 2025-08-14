package io.github.seonrizee.scheduler.domain.comment.service;

import io.github.seonrizee.scheduler.domain.comment.dto.request.CommentCreateRequest;
import io.github.seonrizee.scheduler.domain.comment.dto.request.CommentUpdateRequest;
import io.github.seonrizee.scheduler.domain.comment.dto.response.CommentDetailResponse;
import io.github.seonrizee.scheduler.domain.comment.entity.Comment;
import io.github.seonrizee.scheduler.domain.comment.mapper.CommentMapper;
import io.github.seonrizee.scheduler.domain.comment.repository.CommentRepository;
import io.github.seonrizee.scheduler.domain.schedule.entity.Schedule;
import io.github.seonrizee.scheduler.domain.schedule.service.ScheduleQueryService;
import io.github.seonrizee.scheduler.domain.user.entity.User;
import io.github.seonrizee.scheduler.global.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * CommentService의 구현체.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentQueryService commentQueryService;
    private final ScheduleQueryService scheduleQueryService;
    private final CommentMapper commentMapper;
    private final AuthService authService;

    /**
     * {@inheritDoc}
     */
    @Override
    public CommentDetailResponse createComment(Long scheduleId, CommentCreateRequest requestDto, User user) {

        Schedule schedule = scheduleQueryService.findScheduleByIdOrThrow(scheduleId);
        Comment newComment = commentMapper.toEntity(requestDto, schedule, user);
        schedule.addComment(newComment);
        commentRepository.save(newComment);

        return commentMapper.toDto(newComment);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public CommentDetailResponse updateComment(Long commentId, CommentUpdateRequest requestDto, User user) {

        Comment savedComment = commentQueryService.findCommentOrElseThrow(commentId);
        authService.validateOwnership(savedComment, user, c -> c.getUser().getId());
        savedComment.updateContent(requestDto.getContent());
        return commentMapper.toDto(savedComment);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteComment(Long commentId, User user) {

        Comment savedComment = commentQueryService.findCommentOrElseThrow(commentId);
        authService.validateOwnership(savedComment, user, c -> c.getUser().getId());
        commentRepository.delete(savedComment);
    }
}
