package io.github.seonrizee.scheduler.service;

import io.github.seonrizee.scheduler.dto.request.CommentCreateRequest;
import io.github.seonrizee.scheduler.dto.request.CommentUpdateRequest;
import io.github.seonrizee.scheduler.dto.response.CommentDetailResponse;
import io.github.seonrizee.scheduler.entity.Comment;
import io.github.seonrizee.scheduler.entity.Schedule;
import io.github.seonrizee.scheduler.entity.User;
import io.github.seonrizee.scheduler.mapper.CommentMapper;
import io.github.seonrizee.scheduler.repository.CommentRepository;
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
        Comment savedComment = commentRepository.save(commentMapper.toEntity(requestDto, schedule, user));
        return commentMapper.toDto(savedComment);
    }


    @Override
    public CommentDetailResponse updateComment(Long commentId, CommentUpdateRequest requestDto, User user) {

        Comment savedComment = commentFinder.getCommentOrElseThrow(commentId);
        authorizationService.validateOwnership(savedComment, user);
        savedComment.updateContent(requestDto.getContent());
        return commentMapper.toDto(savedComment);
    }

    @Override
    public void deleteComment(Long commentId, User user) {

        Comment savedComment = commentFinder.getCommentOrElseThrow(commentId);
        authorizationService.validateOwnership(savedComment, user);
        commentRepository.delete(savedComment);
    }
}
