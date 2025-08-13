package io.github.seonrizee.scheduler.domain.comment.mapper;

import io.github.seonrizee.scheduler.domain.comment.dto.request.CommentCreateRequest;
import io.github.seonrizee.scheduler.domain.comment.dto.response.CommentDetailResponse;
import io.github.seonrizee.scheduler.domain.comment.entity.Comment;
import io.github.seonrizee.scheduler.domain.schedule.entity.Schedule;
import io.github.seonrizee.scheduler.domain.user.entity.User;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public Comment toEntity(CommentCreateRequest requestDto, Schedule schedule, User user) {
        return Comment.builder()
                .content(requestDto.getContent())
                .user(user)
                .schedule(schedule)
                .build();
    }

    public CommentDetailResponse toDto(Comment comment) {
        return new CommentDetailResponse(
                comment.getId(),
                comment.getContent(),
                comment.getUser().getId(),
                comment.getUser().getUsername(),
                comment.getCreatedAt(),
                comment.getUpdatedAt());
    }

    public List<CommentDetailResponse> toDto(List<Comment> comments) {
        return comments.stream()
                .map(this::toDto)
                .toList();
    }
}
