package io.github.seonrizee.scheduler.domain.user.service;

import io.github.seonrizee.scheduler.domain.user.dto.request.UserRegisterRequest;
import io.github.seonrizee.scheduler.domain.user.dto.request.UserUpdateRequest;
import io.github.seonrizee.scheduler.domain.user.dto.response.UserProfileResponse;
import io.github.seonrizee.scheduler.domain.user.entity.User;

/**
 * 사용자 관련 비즈니스 로직을 처리하는 서비스 인터페이스.
 */
public interface UserService {
    /**
     * 사용자를 등록합니다.
     * @param requestDto 사용자 등록 요청 DTO
     * @return 등록된 사용자의 프로필 정보
     */
    UserProfileResponse registerUser(UserRegisterRequest requestDto);

    /**
     * 사용자 프로필을 수정합니다.
     * @param userId 수정할 사용자의 ID
     * @param requestDto 사용자 정보 수정 요청 DTO
     * @param loginUser 현재 로그인한 사용자
     * @return 수정된 사용자의 프로필 정보
     */
    UserProfileResponse updateUserProfile(Long userId, UserUpdateRequest requestDto, User loginUser);

    /**
     * 사용자를 삭제합니다.
     * @param userId 삭제할 사용자의 ID
     * @param loginUser 현재 로그인한 사용자
     */
    void deleteUser(Long userId, User loginUser);
}
