package io.github.seonrizee.scheduler.global.security.config;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

/**
 * BCrypt를 사용한 비밀번호 인코딩을 제공하는 클래스.
 */
@Component
public class PasswordEncoderConfig {

    /**
     * 비밀번호를 BCrypt로 인코딩합니다.
     * @param rawPassword 인코딩할 원본 비밀번호
     * @return 인코딩된 비밀번호
     */
    public String encode(String rawPassword) {
        final int DEFAULT_COST = 12;
        return BCrypt.withDefaults().hashToString(DEFAULT_COST, rawPassword.toCharArray());
    }

    /**
     * 원본 비밀번호와 인코딩된 비밀번호가 일치하는지 확인합니다.
     * @param rawPassword 원본 비밀번호
     * @param encodedPassword 인코딩된 비밀번호
     * @return 일치 여부
     */
    public boolean matches(String rawPassword, String encodedPassword) {
        return BCrypt.verifyer().verify(rawPassword.toCharArray(), encodedPassword).verified;
    }
}
