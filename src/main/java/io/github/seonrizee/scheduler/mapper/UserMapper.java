package io.github.seonrizee.scheduler.mapper;

import io.github.seonrizee.scheduler.dto.request.UserRegisterRequest;
import io.github.seonrizee.scheduler.dto.response.UserListResponse;
import io.github.seonrizee.scheduler.dto.response.UserProfileResponse;
import io.github.seonrizee.scheduler.entity.User;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(UserRegisterRequest requestDto) {
        return User.builder()
                .username(requestDto.getUsername())
                .email(requestDto.getEmail())
                .password(requestDto.getPassword())
                .build();
    }

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
