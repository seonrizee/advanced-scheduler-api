package io.github.seonrizee.scheduler.domain.comment.service;

import io.github.seonrizee.scheduler.domain.comment.dto.request.CommentCreateRequest;
import io.github.seonrizee.scheduler.domain.comment.dto.request.CommentUpdateRequest;
import io.github.seonrizee.scheduler.domain.comment.dto.response.CommentDetailResponse;
import io.github.seonrizee.scheduler.domain.comment.entity.Comment;
import io.github.seonrizee.scheduler.domain.comment.mapper.CommentMapper;
import io.github.seonrizee.scheduler.domain.comment.repository.CommentRepository;
import io.github.seonrizee.scheduler.domain.schedule.entity.Schedule;
import io.github.seonrizee.scheduler.domain.schedule.service.ScheduleFinder;
import io.github.seonrizee.scheduler.domain.user.entity.User;
import io.github.seonrizee.scheduler.global.security.service.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentFinder commentFinder;
    private final ScheduleFinder scheduleFinder;
    private final CommentMapper commentMapper;
    private final AuthorizationService authorizationService;

    @Override
    public CommentDetailResponse createComment(Long scheduleId, CommentCreateRequest requestDto, User user) {

        Schedule schedule = scheduleFinder.findScheduleByIdOrThrow(scheduleId);
        Comment newComment = commentMapper.toEntity(requestDto, schedule, user);
        schedule.addComment(newComment);
        commentRepository.save(newComment);

        return commentMapper.toDto(newComment);
    }


    @Override
    public CommentDetailResponse updateComment(Long commentId, CommentUpdateRequest requestDto, User user) {

        Comment savedComment = commentFinder.findCommentOrElseThrow(commentId);
        authorizationService.validateOwnership(savedComment, user);
        savedComment.updateContent(requestDto.getContent());
        return commentMapper.toDto(savedComment);
    }

    @Override
    public void deleteComment(Long commentId, User user) {

        Comment savedComment = commentFinder.findCommentOrElseThrow(commentId);
        authorizationService.validateOwnership(savedComment, user);
        commentRepository.delete(savedComment);
    }
}
