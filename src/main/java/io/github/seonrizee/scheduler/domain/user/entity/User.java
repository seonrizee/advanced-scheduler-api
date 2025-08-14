package io.github.seonrizee.scheduler.domain.user.entity;

import io.github.seonrizee.scheduler.global.entity.BaseDateTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 사용자 정보를 담는 엔티티.
 */
@Entity
@Getter
@NoArgsConstructor
public class User extends BaseDateTimeEntity {

    /**
     * 사용자 ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    /**
     * 사용자 이름.
     */
    @Column(nullable = false, unique = true)
    private String username;

    /**
     * 이메일.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * 비밀번호.
     */
    @Column(nullable = false)
    private String password;

    /**
     * User 객체를 생성합니다.
     * @param username 사용자 이름
     * @param email 이메일
     * @param password 비밀번호
     */
    @Builder
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    /**
     * 사용자 프로필을 업데이트합니다.
     * @param username 새로운 사용자 이름
     * @param email 새로운 이메일
     */
    public void updateProfile(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
