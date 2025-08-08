package io.github.seonrizee.scheduler.service;

import io.github.seonrizee.scheduler.dto.request.UserCreateRequest;
import io.github.seonrizee.scheduler.dto.response.UserDetailResponse;
import io.github.seonrizee.scheduler.dto.response.UserListResponse;

public interface UserService {
    UserDetailResponse registerUser(UserCreateRequest requestDto);

    UserDetailResponse getUserProfile(Long userId);

    UserListResponse getAllUsers();
}
