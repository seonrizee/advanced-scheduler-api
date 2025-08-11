package io.github.seonrizee.scheduler.controller;

import io.github.seonrizee.scheduler.dto.ApiResponse;
import io.github.seonrizee.scheduler.dto.request.SessionCreateRequest;
import io.github.seonrizee.scheduler.dto.response.UserProfileResponse;
import io.github.seonrizee.scheduler.service.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    public ApiResponse<UserProfileResponse> createSession(@RequestBody SessionCreateRequest requestDto,
                                                          HttpServletRequest request) {

        UserProfileResponse userProfileResponse = sessionService.signIn(requestDto);

        // 세션 생성 및 사용자 ID 저장
        HttpSession session = request.getSession();
        session.setAttribute("userId", userProfileResponse.getId());

        return ApiResponse.ok(userProfileResponse);
    }

}
