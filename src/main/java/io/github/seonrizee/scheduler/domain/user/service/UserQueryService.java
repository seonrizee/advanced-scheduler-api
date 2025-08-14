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

/**
 * 사용자 정보 조회 관련 서비스를 제공합니다.
 */
@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserQueryService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    /**
     * 특정 사용자의 프로필 정보를 조회합니다.
     * @param userId 조회할 사용자의 ID
     * @return 사용자 프로필 정보
     */
    public UserProfileResponse getUserProfile(Long userId) {
        User user = findByIdOrThrow(userId);
        return userMapper.toDto(user);
    }

    /**
     * 모든 사용자의 프로필 정보 목록을 조회합니다.
     * @return 모든 사용자의 프로필 정보 목록
     */
    public List<UserProfileResponse> getAllUsers() {
        return userMapper.toDto(userRepository.findAll());
    }

    /**
     * ID로 사용자를 찾고, 없으면 예외를 발생시킵니다.
     * @param userId 찾을 사용자의 ID
     * @return 찾아낸 사용자 엔티티
     * @throws CustomBusinessException 사용자를 찾지 못한 경우
     */
    public User findByIdOrThrow(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new CustomBusinessException(ErrorCode.USER_NOT_FOUND));
    }
}
