package io.github.seonrizee.scheduler.mapper;

import io.github.seonrizee.scheduler.dto.request.CommentCreateRequest;
import io.github.seonrizee.scheduler.dto.response.CommentDetailResponse;
import io.github.seonrizee.scheduler.entity.Comment;
import io.github.seonrizee.scheduler.entity.Schedule;
import io.github.seonrizee.scheduler.entity.User;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public Comment toEntity(CommentCreateRequest requestDto, User user, Schedule schedule) {
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
                comment.getCreatedAt(),
                comment.getUpdatedAt());
    }
}
