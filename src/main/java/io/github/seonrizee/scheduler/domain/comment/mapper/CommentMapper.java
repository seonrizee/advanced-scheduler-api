package io.github.seonrizee.scheduler.domain.comment.mapper;

import io.github.seonrizee.scheduler.domain.comment.dto.request.CommentCreateRequest;
import io.github.seonrizee.scheduler.domain.comment.dto.response.CommentDetailResponse;
import io.github.seonrizee.scheduler.domain.comment.entity.Comment;
import io.github.seonrizee.scheduler.domain.schedule.entity.Schedule;
import io.github.seonrizee.scheduler.domain.user.entity.User;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Comment 관련 DTO와 Entity 간의 변환을 담당하는 매퍼.
 */
@Component
public class CommentMapper {

    /**
     * CommentCreateRequest DTO를 Comment 엔티티로 변환합니다.
     * @param requestDto    댓글 생성 요청 DTO
     * @param schedule      댓글이 달릴 일정
     * @param user          댓글 작성자
     * @return              Comment 엔티티
     */
    public Comment toEntity(CommentCreateRequest requestDto, Schedule schedule, User user) {
        return Comment.builder()
                .content(requestDto.getContent())
                .user(user)
                .schedule(schedule)
                .build();
    }

    /**
     * Comment 엔티티를 CommentDetailResponse DTO로 변환합니다.
     * @param comment   Comment 엔티티
     * @return          CommentDetailResponse DTO
     */
    public CommentDetailResponse toDto(Comment comment) {
        return new CommentDetailResponse(
                comment.getId(),
                comment.getContent(),
                comment.getUser().getId(),
                comment.getUser().getUsername(),
                comment.getCreatedAt(),
                comment.getUpdatedAt());
    }

    /**
     * Comment 엔티티 리스트를 CommentDetailResponse DTO 리스트로 변환합니다.
     * @param comments  Comment 엔티티 리스트
     * @return          CommentDetailResponse DTO 리스트
     */
    public List<CommentDetailResponse> toDto(List<Comment> comments) {
        return comments.stream()
                .map(this::toDto)
                .toList();
    }
}
