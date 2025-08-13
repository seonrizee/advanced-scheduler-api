package io.github.seonrizee.scheduler.global.security.service;

import io.github.seonrizee.scheduler.domain.user.dto.request.SessionCreateRequest;
import io.github.seonrizee.scheduler.domain.user.dto.response.UserProfileResponse;
import io.github.seonrizee.scheduler.domain.user.entity.User;
import io.github.seonrizee.scheduler.domain.user.mapper.UserMapper;
import io.github.seonrizee.scheduler.domain.user.repository.UserRepository;
import io.github.seonrizee.scheduler.global.code.ErrorCode;
import io.github.seonrizee.scheduler.global.exception.CustomBusinessException;
import io.github.seonrizee.scheduler.global.security.config.PasswordEncoderConfig;
import java.util.function.ToLongFunction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoderConfig passwordEncoderConfig;

    public <T> void validateOwnership(T entity, User loginUser, ToLongFunction<T> ownerIdExtractor) {
        Long ownerId = ownerIdExtractor.applyAsLong(entity);
        if (!ownerId.equals(loginUser.getId())) {
            throw new CustomBusinessException(ErrorCode.NO_PERMISSION); // 권한 없음 예외
        }
    }

    public UserProfileResponse login(SessionCreateRequest requestDto) {

        // 이메일로 사용자 조회
        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new CustomBusinessException(ErrorCode.USER_NOT_FOUND));

        // 비밀번호 확인
        if (!passwordEncoderConfig.matches(requestDto.getPassword(), user.getPassword())) {
            throw new CustomBusinessException(ErrorCode.INVALID_USER);
        }

        return userMapper.toDto(user);
    }
}
