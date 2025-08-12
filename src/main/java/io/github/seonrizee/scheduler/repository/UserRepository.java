package io.github.seonrizee.scheduler.repository;


import io.github.seonrizee.scheduler.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
