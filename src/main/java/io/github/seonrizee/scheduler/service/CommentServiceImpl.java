package io.github.seonrizee.scheduler.service;

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
import io.github.seonrizee.scheduler.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    @Override
    public CommentDetailResponse createComment(Long scheduleId, Long userId, CommentCreateRequest requestDto) {

        User user = userRepository.findByIdOrThrow(userId);
        Schedule schedule = scheduleRepository.findScheduleByIdOrThrow(scheduleId);

        Comment savedComment = commentRepository.save(commentMapper.toEntity(requestDto, user, schedule));
        return commentMapper.toDto(savedComment);
    }

    @Override
    public CommentDetailResponse findCommentById(Long commentId) {
        return null;
    }

    @Override
    public CommentListResponse findCommentsByScheduleId(Long scheduleId) {
        return null;
    }

    @Override
    public CommentDetailResponse updateComment(Long commentId, CommentUpdateRequest requestDto) {
        return null;
    }

    @Override
    public void deleteComment(Long commentId) {

    }
}
