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

@RestController
@RequestMapping("/sessions")
@RequiredArgsConstructor
public class SessionController {

    private final AuthService authService;
    private final SessionManager sessionManager;

    @PostMapping
    public ApiResponse<UserProfileResponse> createSession(@RequestBody @Valid SessionCreateRequest requestDto,
                                                          HttpServletRequest request) {

        UserProfileResponse userProfileResponse = authService.login(requestDto);
        sessionManager.createSession(userProfileResponse.getId());
        return ApiResponse.ok(userProfileResponse);
    }

    @DeleteMapping
    public ApiResponse<Void> deleteSession(HttpServletRequest request) {

        sessionManager.logout();
        return ApiResponse.ok();
    }

}
