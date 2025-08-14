package io.github.seonrizee.scheduler.domain.user.controller;

import io.github.seonrizee.scheduler.domain.user.dto.request.SessionCreateRequest;
import io.github.seonrizee.scheduler.domain.user.dto.response.UserProfileResponse;
import io.github.seonrizee.scheduler.global.dto.ApiResponse;
import io.github.seonrizee.scheduler.global.security.SessionManager;
import io.github.seonrizee.scheduler.global.security.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 세션 관련 API를 제공하는 컨트롤러 (로그인/로그아웃).
 */
@RestController
@RequestMapping("/sessions")
@RequiredArgsConstructor
public class SessionController {

    private final AuthService authService;
    private final SessionManager sessionManager;

    /**
     * 새로운 세션을 생성합니다 (로그인).
     * @param requestDto 세션 생성 요청 데이터
     * @param request HttpServletRequest
     * @return 로그인한 사용자의 프로필 정보
     */
    @PostMapping
    public ApiResponse<UserProfileResponse> createSession(@RequestBody @Valid SessionCreateRequest requestDto,
                                                          HttpServletRequest request) {

        UserProfileResponse userProfileResponse = authService.login(requestDto);
        sessionManager.createSession(userProfileResponse.getId());
        return ApiResponse.ok(userProfileResponse);
    }

    /**
     * 현재 세션을 종료합니다 (로그아웃).
     * @param request HttpServletRequest
     * @return 성공 응답
     */
    @DeleteMapping
    public ApiResponse<Void> deleteSession(HttpServletRequest request) {

        sessionManager.logout();
        return ApiResponse.ok();
    }

}
