package io.github.seonrizee.scheduler.domain.user.controller;

import io.github.seonrizee.scheduler.domain.user.dto.request.UserRegisterRequest;
import io.github.seonrizee.scheduler.domain.user.dto.request.UserUpdateRequest;
import io.github.seonrizee.scheduler.domain.user.dto.response.UserProfileResponse;
import io.github.seonrizee.scheduler.domain.user.entity.User;
import io.github.seonrizee.scheduler.domain.user.service.UserQueryService;
import io.github.seonrizee.scheduler.domain.user.service.UserService;
import io.github.seonrizee.scheduler.global.annotation.LoginUser;
import io.github.seonrizee.scheduler.global.dto.ApiResponse;
import io.github.seonrizee.scheduler.global.security.SessionManager;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserQueryService userQueryService;
    private final SessionManager sessionManager;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<UserProfileResponse> registerUser(@RequestBody @Valid UserRegisterRequest requestDto) {

        UserProfileResponse responseDto = userService.registerUser(requestDto);
        return ApiResponse.created(responseDto);
    }

    @GetMapping
    public ApiResponse<List<UserProfileResponse>> getUsers() {
        List<UserProfileResponse> responseDto = userQueryService.getAllUsers();
        return ApiResponse.ok(responseDto);
    }

    @GetMapping("/{userId}")
    public ApiResponse<UserProfileResponse> getUserProfile(@PathVariable Long userId) {

        UserProfileResponse responseDto = userQueryService.getUserProfile(userId);
        return ApiResponse.ok(responseDto);
    }

    @PatchMapping("/{userId}")
    public ApiResponse<UserProfileResponse> updateUserProfile(@PathVariable Long userId,
                                                              @RequestBody @Valid UserUpdateRequest requestDto,
                                                              @LoginUser User loginUser) {
        UserProfileResponse responseDto = userService.updateUserProfile(userId, requestDto, loginUser);
        return ApiResponse.ok(responseDto);
    }

    @DeleteMapping("/{userId}")
    public ApiResponse<Void> deleteUser(@PathVariable Long userId, @LoginUser User loginUser) {
        userService.deleteUser(userId, loginUser);
        sessionManager.logout();
        return ApiResponse.ok();
    }
}
