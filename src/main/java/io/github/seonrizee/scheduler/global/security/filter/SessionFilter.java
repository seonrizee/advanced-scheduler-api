package io.github.seonrizee.scheduler.global.security.filter;

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
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;

/**
 * 세션 유효성을 검사하는 서블릿 필터.
 */
@Component
public class SessionFilter implements Filter {

    /**
     * 세션 검사를 제외할 엔드포인트 목록.
     * URL 패턴과 허용되는 HTTP 메서드를 정의합니다.
     */
    private static final Map<String, Set<HttpMethod>> excludedEndpoints = Map.of(
            "/sessions", Set.of(HttpMethod.POST),
            "/users", Set.of(HttpMethod.POST),
            "/error", Set.of(HttpMethod.GET, HttpMethod.POST));

    private final HandlerExceptionResolver handlerExceptionResolver;

    /**
     * SessionFilter를 생성합니다.
     * @param handlerExceptionResolver 필터에서 발생하는 예외를 처리하기 위한 Resolver
     */
    public SessionFilter(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver handlerExceptionResolver) {
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    /**
     * 요청이 들어올 때마다 세션 유효성을 검사합니다.
     * 제외된 엔드포인트가 아닐 경우, 세션 존재 여부와 세션에 "userId" 속성이 있는지 확인합니다.
     * @param request  ServletRequest
     * @param response ServletResponse
     * @param chain    FilterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            if (!isExcluded(httpRequest)) {
                // 제외된 URL과 메서드가 아닌 경우 세션 검증
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

    /**
     * 현재 요청이 세션 검사에서 제외되어야 하는지 확인합니다.
     * @param request HttpServletRequest
     * @return 제외 여부
     */
    private boolean isExcluded(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        HttpMethod requestMethod = HttpMethod.valueOf(request.getMethod());

        return excludedEndpoints.entrySet().stream()
                .anyMatch(entry ->
                        PatternMatchUtils.simpleMatch(entry.getKey(), requestURI) &&
                                entry.getValue().contains(requestMethod)
                );
    }
}
