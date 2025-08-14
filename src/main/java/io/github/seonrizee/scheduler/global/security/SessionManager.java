package io.github.seonrizee.scheduler.global.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * HTTP 세션을 관리하는 클래스.
 */
@Component
@RequiredArgsConstructor
public class SessionManager {

    private final HttpServletRequest request;

    /**
     * 새로운 세션을 생성하고 사용자 ID를 저장합니다.
     * @param userId 세션에 저장할 사용자 ID
     */
    public void createSession(Long userId) {
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("userId", userId);
    }

    /**
     * 현재 세션을 무효화하여 로그아웃 처리합니다.
     */
    public void logout() {
        Optional<HttpSession> session = Optional.ofNullable(request.getSession(false));
        session.ifPresent(HttpSession::invalidate);
    }
}
