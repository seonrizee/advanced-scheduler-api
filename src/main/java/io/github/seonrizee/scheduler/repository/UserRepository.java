package io.github.seonrizee.scheduler.repository;


import io.github.seonrizee.scheduler.common.code.ErrorCode;
import io.github.seonrizee.scheduler.entity.User;
import io.github.seonrizee.scheduler.exception.CustomBusinessException;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    default User findByIdOrThrow(Long userId) {
        return findById(userId)
                .orElseThrow(() -> new CustomBusinessException(ErrorCode.USER_NOT_FOUND));
    }

    Optional<User> findByEmail(String email);
}
