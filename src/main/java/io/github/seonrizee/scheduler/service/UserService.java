package io.github.seonrizee.scheduler.service;

import io.github.seonrizee.scheduler.dto.request.UserRegisterRequest;
import io.github.seonrizee.scheduler.dto.request.UserUpdateRequest;
import io.github.seonrizee.scheduler.dto.response.UserProfileResponse;

public interface UserService {
    UserProfileResponse registerUser(UserRegisterRequest requestDto);

    UserProfileResponse updateUserProfile(Long userId, UserUpdateRequest requestDto);

    void deleteUser(Long userId);
}
