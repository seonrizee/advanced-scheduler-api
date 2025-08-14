package io.github.seonrizee.scheduler.domain.user.mapper;

import io.github.seonrizee.scheduler.domain.user.dto.request.UserRegisterRequest;
import io.github.seonrizee.scheduler.domain.user.dto.response.UserProfileResponse;
import io.github.seonrizee.scheduler.domain.user.entity.User;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * User 관련 DTO와 Entity 간의 변환을 담당하는 매퍼.
 */
@Component
public class UserMapper {

    /**
     * UserRegisterRequest DTO와 인코딩된 비밀번호를 User 엔티티로 변환합니다.
     * @param requestDto        사용자 등록 요청 DTO
     * @param encodedPassword   인코딩된 비밀번호
     * @return                  User 엔티티
     */
    public User toEntity(UserRegisterRequest requestDto, String encodedPassword) {
        return User.builder()
                .username(requestDto.getUsername())
                .email(requestDto.getEmail())
                .password(encodedPassword)
                .build();
    }

    /**
     * User 엔티티를 UserProfileResponse DTO로 변환합니다.
     * @param user  User 엔티티
     * @return      UserProfileResponse DTO
     */
    public UserProfileResponse toDto(User user) {
        return new UserProfileResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    /**
     * User 엔티티 리스트를 UserProfileResponse DTO 리스트로 변환합니다.
     * @param users User 엔티티 리스트
     * @return      UserProfileResponse DTO 리스트
     */
    public List<UserProfileResponse> toDto(List<User> users) {
        return users.stream()
                .map(this::toDto)
                .toList();

    }
}
