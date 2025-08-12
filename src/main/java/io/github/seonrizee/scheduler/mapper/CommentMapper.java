package io.github.seonrizee.scheduler.mapper;

import io.github.seonrizee.scheduler.dto.request.CommentCreateRequest;
import io.github.seonrizee.scheduler.dto.response.CommentDetailResponse;
import io.github.seonrizee.scheduler.entity.Comment;
import io.github.seonrizee.scheduler.entity.Schedule;
import io.github.seonrizee.scheduler.entity.User;
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
