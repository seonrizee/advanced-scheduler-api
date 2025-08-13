package io.github.seonrizee.scheduler.security.filter;

import io.github.seonrizee.scheduler.global.code.ErrorCode;
import io.github.seonrizee.scheduler.global.exception.CustomBusinessException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Component
public class SessionFilter implements Filter {

    private static final String[] excludedUrls = {"/sessions", "/users", "/error"};

    private final HandlerExceptionResolver handlerExceptionResolver;

    public SessionFilter(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver handlerExceptionResolver) {
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;
        final String requestURI = httpRequest.getRequestURI();

        try {
            if (!isExcluded(requestURI)) {
                // 제외된 URL이 아닌 경우 세션 검증
                HttpSession session = httpRequest.getSession(false);
                if (session == null || session.getAttribute("userId") == null) {
                    // 세션이 없으면 예외 발생
                    throw new CustomBusinessException(ErrorCode.USER_NOT_AUTHORIZED);
                }
            }
            chain.doFilter(request, response);
        } catch (CustomBusinessException e) {
            // 발생한 예외를 HandlerExceptionResolver에게 위임하여 처리
            handlerExceptionResolver.resolveException(httpRequest, httpResponse, null, e);
        }
    }

    private boolean isExcluded(String requestURI) {
        return PatternMatchUtils.simpleMatch(excludedUrls, requestURI);
    }
}
