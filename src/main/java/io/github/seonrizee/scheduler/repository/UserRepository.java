package io.github.seonrizee.scheduler.repository;


import io.github.seonrizee.scheduler.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
