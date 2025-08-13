package io.github.seonrizee.scheduler.global.security.service;

import io.github.seonrizee.scheduler.domain.user.entity.User;
import io.github.seonrizee.scheduler.global.code.ErrorCode;
import io.github.seonrizee.scheduler.global.entity.Ownable;
import io.github.seonrizee.scheduler.global.exception.CustomBusinessException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    public void validateOwnership(Ownable entity, User loginUser) {
        if (!entity.getUser().getId().equals(loginUser.getId())) {
            throw new CustomBusinessException(ErrorCode.NO_PERMISSION); // 권한 없음 예외
        }
    }
}
