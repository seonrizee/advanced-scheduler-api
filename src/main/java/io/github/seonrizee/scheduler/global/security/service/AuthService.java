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

/**
 * 인증 및 권한 부여 관련 서비스를 제공합니다.
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoderConfig passwordEncoderConfig;

    /**
     * 특정 엔티티에 대한 소유권을 검증합니다. 소유자가 아니면 예외를 발생시킵니다.
     * @param <T> 엔티티의 타입
     * @param entity 검증할 엔티티
     * @param loginUser 현재 로그인한 사용자
     * @param ownerIdExtractor 엔티티에서 소유자 ID를 추출하는 함수
     */
    public <T> void validateOwnership(T entity, User loginUser, ToLongFunction<T> ownerIdExtractor) {
        Long ownerId = ownerIdExtractor.applyAsLong(entity);
        if (!ownerId.equals(loginUser.getId())) {
            throw new CustomBusinessException(ErrorCode.NO_PERMISSION); // 권한 없음 예외
        }
    }

    /**
     * 사용자 로그인을 처리합니다. 이메일과 비밀번호를 확인하고, 성공 시 사용자 프로필 정보를 반환합니다.
     * @param requestDto 로그인 요청 데이터
     * @return 로그인한 사용자의 프로필 정보
     */
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
