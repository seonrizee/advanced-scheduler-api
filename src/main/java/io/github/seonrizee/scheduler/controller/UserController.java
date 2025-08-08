package io.github.seonrizee.scheduler.controller;

import io.github.seonrizee.scheduler.dto.ApiResponse;
import io.github.seonrizee.scheduler.dto.request.UserRegisterRequest;
import io.github.seonrizee.scheduler.dto.request.UserUpdateRequest;
import io.github.seonrizee.scheduler.dto.response.UserListResponse;
import io.github.seonrizee.scheduler.dto.response.UserProfileResponse;
import io.github.seonrizee.scheduler.service.UserService;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<UserProfileResponse> registerUser(@RequestBody UserRegisterRequest requestDto) {

        UserProfileResponse responseDto = userService.registerUser(requestDto);
        return ApiResponse.created(responseDto);
    }

    @GetMapping
    public ApiResponse<UserListResponse> getUsers() {
        UserListResponse responseDto = userService.getAllUsers();
        return ApiResponse.ok(responseDto);
    }

    @GetMapping("/{userId}")
    public ApiResponse<UserProfileResponse> getUserProfile(@PathVariable Long userId) {

        UserProfileResponse responseDto = userService.getUserProfile(userId);
        return ApiResponse.ok(responseDto);
    }

    @PatchMapping("/{userId}")
    public ApiResponse<UserProfileResponse> updateUserProfile(@PathVariable Long userId,
                                                              @RequestBody UserUpdateRequest requestDto) {
        UserProfileResponse responseDto = userService.updateUserProfile(userId, requestDto);
        return ApiResponse.ok(responseDto);
    }

    @DeleteMapping("/{userId}")
    public ApiResponse<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ApiResponse.ok();
    }
}
