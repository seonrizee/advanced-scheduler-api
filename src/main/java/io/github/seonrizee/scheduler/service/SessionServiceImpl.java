package io.github.seonrizee.scheduler.service;

import io.github.seonrizee.scheduler.common.code.ErrorCode;
import io.github.seonrizee.scheduler.config.PasswordEncoderConfig;
import io.github.seonrizee.scheduler.dto.request.SessionCreateRequest;
import io.github.seonrizee.scheduler.dto.response.UserProfileResponse;
import io.github.seonrizee.scheduler.entity.User;
import io.github.seonrizee.scheduler.exception.CustomBusinessException;
import io.github.seonrizee.scheduler.mapper.UserMapper;
import io.github.seonrizee.scheduler.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoderConfig passwordEncoderConfig;

    @Override
    public UserProfileResponse signIn(SessionCreateRequest requestDto) {

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
