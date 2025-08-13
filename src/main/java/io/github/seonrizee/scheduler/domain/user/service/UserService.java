package io.github.seonrizee.scheduler.domain.user.service;

import io.github.seonrizee.scheduler.domain.user.dto.request.UserRegisterRequest;
import io.github.seonrizee.scheduler.domain.user.dto.request.UserUpdateRequest;
import io.github.seonrizee.scheduler.domain.user.dto.response.UserProfileResponse;

public interface UserService {
    UserProfileResponse registerUser(UserRegisterRequest requestDto);

    UserProfileResponse updateUserProfile(Long userId, UserUpdateRequest requestDto);

    void deleteUser(Long userId);
}
