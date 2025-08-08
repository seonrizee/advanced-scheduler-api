package io.github.seonrizee.scheduler.service;

import io.github.seonrizee.scheduler.dto.request.UserCreateRequest;
import io.github.seonrizee.scheduler.dto.response.UserDetailResponse;

public interface UserService {
    UserDetailResponse registerUser(UserCreateRequest requestDto);

    UserDetailResponse getUserProfile(Long userId);
}
