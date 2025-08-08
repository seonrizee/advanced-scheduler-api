package io.github.seonrizee.scheduler.controller;

import io.github.seonrizee.scheduler.dto.ApiResponse;
import io.github.seonrizee.scheduler.dto.request.UserCreateRequest;
import io.github.seonrizee.scheduler.dto.response.UserDetailResponse;
import io.github.seonrizee.scheduler.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse<UserDetailResponse>> registerUser(@RequestBody UserCreateRequest requestDto) {

        UserDetailResponse responseDto = userService.registerUser(requestDto);
        return ApiResponse.created(responseDto);
    }
}
