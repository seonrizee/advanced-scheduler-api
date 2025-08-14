package io.github.seonrizee.scheduler.domain.user.service;

import io.github.seonrizee.scheduler.domain.user.dto.response.UserProfileResponse;
import io.github.seonrizee.scheduler.domain.user.entity.User;
import io.github.seonrizee.scheduler.domain.user.mapper.UserMapper;
import io.github.seonrizee.scheduler.domain.user.repository.UserRepository;
import io.github.seonrizee.scheduler.global.code.ErrorCode;
import io.github.seonrizee.scheduler.global.exception.CustomBusinessException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserQueryService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserProfileResponse getUserProfile(Long userId) {
        User user = findByIdOrThrow(userId);
        return userMapper.toDto(user);
    }

    public List<UserProfileResponse> getAllUsers() {
        return userMapper.toDto(userRepository.findAll());
    }

    public User findByIdOrThrow(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new CustomBusinessException(ErrorCode.USER_NOT_FOUND));
    }
}
