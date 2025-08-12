package io.github.seonrizee.scheduler.service;

import io.github.seonrizee.scheduler.common.code.ErrorCode;
import io.github.seonrizee.scheduler.common.exception.CustomBusinessException;
import io.github.seonrizee.scheduler.dto.response.UserListResponse;
import io.github.seonrizee.scheduler.dto.response.UserProfileResponse;
import io.github.seonrizee.scheduler.entity.User;
import io.github.seonrizee.scheduler.mapper.UserMapper;
import io.github.seonrizee.scheduler.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserFinder {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserProfileResponse getUserProfile(Long userId) {
        User user = findByIdOrThrow(userId);
        return userMapper.toDto(user);
    }

    public UserListResponse getAllUsers() {
        return userMapper.toDto(userRepository.findAll());
    }


    public User findByIdOrThrow(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new CustomBusinessException(ErrorCode.USER_NOT_FOUND));
    }
}
