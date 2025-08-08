package io.github.seonrizee.scheduler.mapper;

import io.github.seonrizee.scheduler.dto.request.UserCreateRequest;
import io.github.seonrizee.scheduler.dto.response.UserDetailResponse;
import io.github.seonrizee.scheduler.dto.response.UserListResponse;
import io.github.seonrizee.scheduler.entity.User;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(UserCreateRequest requestDto) {
        return User.builder()
                .username(requestDto.getUsername())
                .email(requestDto.getEmail())
                .build();
    }

    public UserDetailResponse toDto(User user) {
        return new UserDetailResponse(
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
