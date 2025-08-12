package io.github.seonrizee.scheduler.service;

import io.github.seonrizee.scheduler.common.code.ErrorCode;
import io.github.seonrizee.scheduler.common.exception.CustomBusinessException;
import io.github.seonrizee.scheduler.dto.request.CommentCreateRequest;
import io.github.seonrizee.scheduler.dto.request.CommentUpdateRequest;
import io.github.seonrizee.scheduler.dto.response.CommentDetailResponse;
import io.github.seonrizee.scheduler.dto.response.CommentListResponse;
import io.github.seonrizee.scheduler.entity.Comment;
import io.github.seonrizee.scheduler.entity.Schedule;
import io.github.seonrizee.scheduler.entity.User;
import io.github.seonrizee.scheduler.mapper.CommentMapper;
import io.github.seonrizee.scheduler.repository.CommentRepository;
import io.github.seonrizee.scheduler.repository.ScheduleRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserFinder userFinder;
    private final CommentMapper commentMapper;

    @Override
    @Transactional
    public CommentDetailResponse createComment(Long scheduleId, CommentCreateRequest requestDto, User user) {

        Schedule schedule = scheduleRepository.findScheduleByIdOrThrow(scheduleId);
        Comment savedComment = commentRepository.save(commentMapper.toEntity(requestDto, schedule, user));
        return commentMapper.toDto(savedComment);
    }

    @Override
    @Transactional(readOnly = true)
    public CommentDetailResponse getComment(Long commentId) {

        Comment savedComment = getCommentOrElseThrow(commentId);
        return commentMapper.toDto(savedComment);
    }


    @Override
    @Transactional(readOnly = true)
    public CommentListResponse getCommentsWithSchedule(Long scheduleId) {

        Schedule schedule = scheduleRepository.findScheduleByIdOrThrow(scheduleId);
        List<Comment> comments = commentRepository.findAllBySchedule(schedule);
        return commentMapper.toDto(comments);
    }

    @Override
    @Transactional
    public CommentDetailResponse updateComment(Long commentId, CommentUpdateRequest requestDto, User user) {

        // TODO 세부 인가 - 내가 작성한건지 확인 필요
        Comment savedComment = getCommentOrElseThrow(commentId);
        savedComment.updateContent(requestDto.getContent());
        return commentMapper.toDto(savedComment);
    }

    @Override
    @Transactional
    public void deleteComment(Long commentId, User user) {

        // TODO 세부 인가 - 내가 작성한건지 확인 필요
        Comment savedComment = getCommentOrElseThrow(commentId);
        commentRepository.delete(savedComment);
    }

    private Comment getCommentOrElseThrow(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomBusinessException(ErrorCode.COMMENT_NOT_FOUND));
    }
}
