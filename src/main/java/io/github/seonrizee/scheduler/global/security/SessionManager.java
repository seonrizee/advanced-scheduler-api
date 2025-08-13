package io.github.seonrizee.scheduler.global.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionManager {

    private final HttpServletRequest request;

    public void createSession(Long userId) {
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("userId", userId);
    }

    public void logout() {
        Optional<HttpSession> session = Optional.ofNullable(request.getSession(false));
        session.ifPresent(HttpSession::invalidate);
    }
}
