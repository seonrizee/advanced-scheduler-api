package io.github.seonrizee.scheduler.domain.user.repository;


import io.github.seonrizee.scheduler.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
