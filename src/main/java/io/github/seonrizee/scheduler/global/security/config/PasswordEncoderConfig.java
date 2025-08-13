package io.github.seonrizee.scheduler.global.security.config;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderConfig {

    public String encode(String rawPassword) {
        final int DEFAULT_COST = 12;
        return BCrypt.withDefaults().hashToString(DEFAULT_COST, rawPassword.toCharArray());
    }

    public boolean matches(String rawPassword, String encodedPassword) {
        return BCrypt.verifyer().verify(rawPassword.toCharArray(), encodedPassword).verified;
    }
}
