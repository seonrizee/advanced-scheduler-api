package io.github.seonrizee.scheduler.global.entity;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 생성 및 수정 일시를 자동으로 관리하기 위한 기본 엔티티 클래스.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class BaseDateTimeEntity {


    /**
     * 생성 일시.
     */
    @CreatedDate
    @Column(updatable = false, name = "created_at")
    private LocalDateTime createdAt;


    /**
     * 수정 일시.
     */
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
