package io.github.seonrizee.scheduler.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * JPA Auditing을 활성화하는 설정 클래스.
 */
@Configuration
@EnableJpaAuditing
public class JpaAuditingConfig {
}
