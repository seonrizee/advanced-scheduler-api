package io.github.seonrizee.scheduler.repository;

import io.github.seonrizee.scheduler.entity.Schedule;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("SELECT s FROM Schedule s LEFT JOIN FETCH s.comments WHERE s.id = :id")
    Optional<Schedule> findWithCommentsById(@Param("id") Long id);
}
