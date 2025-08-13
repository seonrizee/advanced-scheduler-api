package io.github.seonrizee.scheduler.global.security.service;

import io.github.seonrizee.scheduler.domain.user.entity.User;
import io.github.seonrizee.scheduler.global.code.ErrorCode;
import io.github.seonrizee.scheduler.global.exception.CustomBusinessException;
import java.util.function.ToLongFunction;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    public <T> void validateOwnership(T entity, User loginUser, ToLongFunction<T> ownerIdExtractor) {
        Long ownerId = ownerIdExtractor.applyAsLong(entity);
        if (!ownerId.equals(loginUser.getId())) {
            throw new CustomBusinessException(ErrorCode.NO_PERMISSION); // 권한 없음 예외
        }
    }
}
