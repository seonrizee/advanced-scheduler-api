package io.github.seonrizee.scheduler.domain.comment.service;

import io.github.seonrizee.scheduler.domain.comment.dto.request.CommentCreateRequest;
import io.github.seonrizee.scheduler.domain.comment.dto.request.CommentUpdateRequest;
import io.github.seonrizee.scheduler.domain.comment.dto.response.CommentDetailResponse;
import io.github.seonrizee.scheduler.domain.user.entity.User;

/**
 * 댓글 관련 비즈니스 로직을 처리하는 서비스 인터페이스.
 */
public interface CommentService {

    /**
     * 새로운 댓글을 생성합니다.
     * @param scheduleId 댓글을 추가할 일정의 ID
     * @param requestDto 댓글 생성 요청 데이터
     * @param user 현재 로그인한 사용자
     * @return 생성된 댓글의 상세 정보
     */
    CommentDetailResponse createComment(Long scheduleId, CommentCreateRequest requestDto, User user);

    /**
     * 댓글을 수정합니다.
     * @param commentId 수정할 댓글의 ID
     * @param requestDto 댓글 수정 요청 데이터
     * @param user 현재 로그인한 사용자
     * @return 수정된 댓글의 상세 정보
     */
    CommentDetailResponse updateComment(Long commentId, CommentUpdateRequest requestDto, User user);

    /**
     * 댓글을 삭제합니다.
     * @param commentId 삭제할 댓글의 ID
     * @param user 현재 로그인한 사용자
     */
    void deleteComment(Long commentId, User user);

}
