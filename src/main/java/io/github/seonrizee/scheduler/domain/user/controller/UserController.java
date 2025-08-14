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

/**
 * 사용자 관련 API를 제공하는 컨트롤러.
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserQueryService userQueryService;
    private final SessionManager sessionManager;

    /**
     * 새로운 사용자를 등록합니다.
     * @param requestDto 사용자 등록 요청 데이터
     * @return 생성된 사용자의 프로필 정보
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<UserProfileResponse> registerUser(@RequestBody @Valid UserRegisterRequest requestDto) {

        UserProfileResponse responseDto = userService.registerUser(requestDto);
        return ApiResponse.created(responseDto);
    }

    /**
     * 모든 사용자 목록을 조회합니다.
     * @return 사용자 프로필 목록
     */
    @GetMapping
    public ApiResponse<List<UserProfileResponse>> getUsers() {
        List<UserProfileResponse> responseDto = userQueryService.getAllUsers();
        return ApiResponse.ok(responseDto);
    }

    /**
     * 특정 사용자의 프로필을 조회합니다.
     * @param userId 조회할 사용자의 ID
     * @return 사용자 프로필 정보
     */
    @GetMapping("/{userId}")
    public ApiResponse<UserProfileResponse> getUserProfile(@PathVariable Long userId) {

        UserProfileResponse responseDto = userQueryService.getUserProfile(userId);
        return ApiResponse.ok(responseDto);
    }

    /**
     * 사용자 프로필을 수정합니다.
     * @param userId 수정할 사용자의 ID
     * @param requestDto 수정할 사용자 정보
     * @param loginUser 현재 로그인한 사용자
     * @return 수정된 사용자 프로필 정보
     */
    @PatchMapping("/{userId}")
    public ApiResponse<UserProfileResponse> updateUserProfile(@PathVariable Long userId,
                                                              @RequestBody @Valid UserUpdateRequest requestDto,
                                                              @LoginUser User loginUser) {
        UserProfileResponse responseDto = userService.updateUserProfile(userId, requestDto, loginUser);
        return ApiResponse.ok(responseDto);
    }

    /**
     * 사용자를 삭제합니다.
     * @param userId 삭제할 사용자의 ID
     * @param loginUser 현재 로그인한 사용자
     * @return 성공 응답
     */
    @DeleteMapping("/{userId}")
    public ApiResponse<Void> deleteUser(@PathVariable Long userId, @LoginUser User loginUser) {
        userService.deleteUser(userId, loginUser);
        sessionManager.logout();
        return ApiResponse.ok();
    }
}
