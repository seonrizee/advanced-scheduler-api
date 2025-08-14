package io.github.seonrizee.scheduler.global.config.web;

import io.github.seonrizee.scheduler.domain.user.entity.User;
import io.github.seonrizee.scheduler.domain.user.service.UserQueryService;
import io.github.seonrizee.scheduler.global.annotation.LoginUser;
import io.github.seonrizee.scheduler.global.code.ErrorCode;
import io.github.seonrizee.scheduler.global.exception.CustomBusinessException;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @LoginUser 어노테이션이 붙은 파라미터에 현재 로그인한 사용자 객체를 주입하는 Argument Resolver.
 */
@Component
@RequiredArgsConstructor
public class LoginUserIdArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;
    private final UserQueryService userQueryService;

    /**
     * @LoginUser 어노테이션이 있고, 파라미터 타입이 User 클래스인 경우에만 Resolver가 동작하도록 지정합니다.
     * @param parameter 검사할 파라미터
     * @return 지원 여부
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        boolean hasAnnotation = parameter.hasParameterAnnotation(LoginUser.class);
        boolean hasType = User.class.isAssignableFrom(parameter.getParameterType());
        return hasAnnotation && hasType;
    }

    /**
     * 세션에서 사용자 ID를 가져와 User 객체를 찾아 반환합니다.
     * @param parameter     메서드 파라미터
     * @param mavContainer  ModelAndView 컨테이너
     * @param webRequest    웹 요청
     * @param binderFactory 데이터 바인더 팩토리
     * @return 현재 로그인한 사용자 객체
     * @throws Exception 세션에 사용자 ID가 없거나 유효하지 않은 경우
     */
    @Override
    public User resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        Object userId = httpSession.getAttribute("userId");
        if (Optional.ofNullable(userId).isEmpty()) {
            throw new CustomBusinessException(ErrorCode.INCONSISTENT_SESSION_STATE);
        }

        return userQueryService.findByIdOrThrow((Long) userId);
    }
}
