package io.github.seonrizee.scheduler.domain.user.service;

import io.github.seonrizee.scheduler.domain.user.dto.request.UserRegisterRequest;
import io.github.seonrizee.scheduler.domain.user.dto.request.UserUpdateRequest;
import io.github.seonrizee.scheduler.domain.user.dto.response.UserProfileResponse;
import io.github.seonrizee.scheduler.domain.user.entity.User;
import io.github.seonrizee.scheduler.domain.user.mapper.UserMapper;
import io.github.seonrizee.scheduler.domain.user.repository.UserRepository;
import io.github.seonrizee.scheduler.global.config.security.PasswordEncoderConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserFinder userFinder;
    private final UserMapper userMapper;
    private final PasswordEncoderConfig passwordEncoderConfig;

    @Override
    public UserProfileResponse registerUser(UserRegisterRequest requestDto) {

        String encodedPassword = passwordEncoderConfig.encode(requestDto.getPassword());
        User savedUser = userRepository.save(userMapper.toEntity(requestDto, encodedPassword));
        return userMapper.toDto(savedUser);
    }


    @Override
    public UserProfileResponse updateUserProfile(Long userId, UserUpdateRequest requestDto) {
        User existingUser = userFinder.findByIdOrThrow(userId);
        existingUser.updateProfile(requestDto.getUsername(), requestDto.getEmail());
        return userMapper.toDto(existingUser);
    }

    @Override
    public void deleteUser(Long userId) {
        User existingUser = userFinder.findByIdOrThrow(userId);
        userRepository.delete(existingUser);
    }
}
