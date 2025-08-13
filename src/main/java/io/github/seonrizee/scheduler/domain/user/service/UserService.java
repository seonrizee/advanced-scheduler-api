package io.github.seonrizee.scheduler.domain.user.service;

import io.github.seonrizee.scheduler.domain.user.dto.request.UserRegisterRequest;
import io.github.seonrizee.scheduler.domain.user.dto.request.UserUpdateRequest;
import io.github.seonrizee.scheduler.domain.user.dto.response.UserProfileResponse;
import io.github.seonrizee.scheduler.domain.user.entity.User;

public interface UserService {
    UserProfileResponse registerUser(UserRegisterRequest requestDto);

    UserProfileResponse updateUserProfile(Long userId, UserUpdateRequest requestDto, User loginUser);

    void deleteUser(Long userId, User loginUser);
}
