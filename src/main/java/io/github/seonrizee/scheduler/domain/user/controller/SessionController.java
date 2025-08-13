package io.github.seonrizee.scheduler.domain.user.controller;

import io.github.seonrizee.scheduler.domain.user.dto.request.SessionCreateRequest;
import io.github.seonrizee.scheduler.domain.user.dto.response.UserProfileResponse;
import io.github.seonrizee.scheduler.domain.user.service.SessionService;
import io.github.seonrizee.scheduler.global.dto.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sessions")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    // Example endpoint method
    @PostMapping
    public ApiResponse<UserProfileResponse> createSession(@RequestBody @Valid SessionCreateRequest requestDto,
                                                          HttpServletRequest request) {

        UserProfileResponse userProfileResponse = sessionService.login(requestDto);

        // 세션 생성 및 사용자 ID 저장
        HttpSession session = request.getSession();
        session.setAttribute("userId", userProfileResponse.getId());

        return ApiResponse.ok(userProfileResponse);
    }

}
