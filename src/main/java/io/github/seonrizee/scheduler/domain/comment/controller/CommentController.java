package io.github.seonrizee.scheduler.domain.comment.controller;

import io.github.seonrizee.scheduler.domain.comment.dto.request.CommentCreateRequest;
import io.github.seonrizee.scheduler.domain.comment.dto.request.CommentUpdateRequest;
import io.github.seonrizee.scheduler.domain.comment.dto.response.CommentDetailResponse;
import io.github.seonrizee.scheduler.domain.comment.service.CommentQueryService;
import io.github.seonrizee.scheduler.domain.comment.service.CommentService;
import io.github.seonrizee.scheduler.domain.user.entity.User;
import io.github.seonrizee.scheduler.global.annotation.LoginUser;
import io.github.seonrizee.scheduler.global.dto.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 댓글 관련 API를 제공하는 컨트롤러.
 */
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final CommentQueryService commentQueryService;

    /**
     * 특정 일정에 새로운 댓글을 생성합니다.
     * @param scheduleId 댓글을 추가할 일정의 ID
     * @param requestDto 댓글 생성 요청 데이터
     * @param user 현재 로그인한 사용자
     * @return 생성된 댓글의 상세 정보
     */
    @PostMapping("/schedules/{scheduleId}/comments")
    public ApiResponse<CommentDetailResponse> createComment(@PathVariable Long scheduleId,
                                                            @Valid @RequestBody CommentCreateRequest requestDto,
                                                            @LoginUser User user
    ) {

        CommentDetailResponse responseDto = commentService.createComment(scheduleId, requestDto, user);
        return ApiResponse.created(responseDto);
    }

    /**
     * 특정 일정의 모든 댓글 목록을 조회합니다.
     * @param scheduleId 댓글 목록을 조회할 일정의 ID
     * @return 댓글 상세 정보 목록
     */
    @GetMapping("/schedules/{scheduleId}/comments")
    public ApiResponse<List<CommentDetailResponse>> getCommentsWithSchedule(@PathVariable Long scheduleId) {

        List<CommentDetailResponse> responseDto = commentQueryService.getCommentsWithSchedule(scheduleId);
        return ApiResponse.ok(responseDto);
    }

    /**
     * 특정 댓글의 상세 정보를 조회합니다.
     * @param commentId 조회할 댓글의 ID
     * @return 댓글 상세 정보
     */
    @GetMapping("/comments/{commentId}")
    public ApiResponse<CommentDetailResponse> getComment(@PathVariable Long commentId) {
        CommentDetailResponse responseDto = commentQueryService.getComment(commentId);
        return ApiResponse.ok(responseDto);
    }

    /**
     * 댓글을 수정합니다.
     * @param commentId 수정할 댓글의 ID
     * @param requestDto 수정할 댓글 내용
     * @param user 현재 로그인한 사용자
     * @return 수정된 댓글의 상세 정보
     */
    @PatchMapping("/comments/{commentId}")
    public ApiResponse<CommentDetailResponse> updateComment(@PathVariable Long commentId,
                                                            @Valid @RequestBody CommentUpdateRequest requestDto,
                                                            @LoginUser User user) {
        CommentDetailResponse responseDto = commentService.updateComment(commentId, requestDto, user);
        return ApiResponse.ok(responseDto);
    }

    /**
     * 댓글을 삭제합니다.
     * @param commentId 삭제할 댓글의 ID
     * @param user 현재 로그인한 사용자
     * @return 성공 응답
     */
    @DeleteMapping("/comments/{commentId}")
    public ApiResponse<Void> deleteComment(@PathVariable Long commentId, @LoginUser User user) {
        commentService.deleteComment(commentId, user);
        return ApiResponse.ok();
    }
}
