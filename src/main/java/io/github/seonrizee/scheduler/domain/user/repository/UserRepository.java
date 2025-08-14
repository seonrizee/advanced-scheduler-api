package io.github.seonrizee.scheduler.domain.user.repository;


import io.github.seonrizee.scheduler.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User 엔티티에 대한 데이터베이스 액세스를 제공하는 리포지토리.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 이메일로 사용자를 찾습니다.
     * @param email 찾을 사용자의 이메일
     * @return Optional<User> 객체
     */
    Optional<User> findByEmail(String email);
}
