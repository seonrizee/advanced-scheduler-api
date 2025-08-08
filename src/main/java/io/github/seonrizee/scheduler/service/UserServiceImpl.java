package io.github.seonrizee.scheduler.service;

import io.github.seonrizee.scheduler.dto.request.UserCreateRequest;
import io.github.seonrizee.scheduler.dto.request.UserUpdateRequest;
import io.github.seonrizee.scheduler.dto.response.UserDetailResponse;
import io.github.seonrizee.scheduler.dto.response.UserListResponse;
import io.github.seonrizee.scheduler.entity.User;
import io.github.seonrizee.scheduler.mapper.UserMapper;
import io.github.seonrizee.scheduler.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserDetailResponse registerUser(UserCreateRequest requestDto) {

        User savedUser = userRepository.save(userMapper.toEntity(requestDto));
        return userMapper.toDto(savedUser);
    }

    @Override
    public UserDetailResponse getUserProfile(Long userId) {
        User user = userRepository.findByIdOrThrow(userId);
        return userMapper.toDto(user);
    }

    @Override
    public UserListResponse getAllUsers() {
        return userMapper.toDto(userRepository.findAll());
    }

    @Override
    @Transactional
    public UserDetailResponse updateUserProfile(Long userId, UserUpdateRequest requestDto) {
        User existingUser = userRepository.findByIdOrThrow(userId);
        existingUser.updateProfile(requestDto.getUsername(), requestDto.getEmail());
        return userMapper.toDto(existingUser);
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        User existingUser = userRepository.findByIdOrThrow(userId);
        userRepository.delete(existingUser);
    }
}
