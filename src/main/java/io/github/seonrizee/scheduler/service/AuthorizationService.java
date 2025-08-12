package io.github.seonrizee.scheduler.service;

import io.github.seonrizee.scheduler.common.code.ErrorCode;
import io.github.seonrizee.scheduler.common.entity.Ownable;
import io.github.seonrizee.scheduler.common.exception.CustomBusinessException;
import io.github.seonrizee.scheduler.entity.User;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    public void validateOwnership(Ownable entity, User loginUser) {
        if (!entity.getUser().getId().equals(loginUser.getId())) {
            throw new CustomBusinessException(ErrorCode.NO_PERMISSION); // 권한 없음 예외
        }
    }
}
