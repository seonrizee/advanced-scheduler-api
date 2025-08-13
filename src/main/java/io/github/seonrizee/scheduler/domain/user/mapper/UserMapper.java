package io.github.seonrizee.scheduler.domain.user.mapper;

import io.github.seonrizee.scheduler.domain.user.dto.request.UserRegisterRequest;
import io.github.seonrizee.scheduler.domain.user.dto.response.UserListResponse;
import io.github.seonrizee.scheduler.domain.user.dto.response.UserProfileResponse;
import io.github.seonrizee.scheduler.domain.user.entity.User;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    
    public User toEntity(UserRegisterRequest requestDto, String encodedPassword) {
        return User.builder()
                .username(requestDto.getUsername())
                .email(requestDto.getEmail())
                .password(encodedPassword)
                .build();
    }

    public UserProfileResponse toDto(User user) {
        return new UserProfileResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    public UserListResponse toDto(List<User> users) {
        return new UserListResponse(
                users.stream()
                        .map(this::toDto)
                        .toList()
        );
    }
}
