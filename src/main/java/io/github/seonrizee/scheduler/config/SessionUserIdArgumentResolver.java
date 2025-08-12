package io.github.seonrizee.scheduler.config;

import io.github.seonrizee.scheduler.common.annotation.SessionUser;
import io.github.seonrizee.scheduler.service.UserFinder;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class SessionUserIdArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;
    private final UserFinder userFinder;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        boolean hasLoginUserAnnotation = parameter.hasParameterAnnotation(SessionUser.class);
        boolean hasLongType = Long.class.isAssignableFrom(parameter.getParameterType());
        return hasLoginUserAnnotation && hasLongType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        Object userId = httpSession.getAttribute("userId");
        return Optional.ofNullable(userId)
                .map(id -> (Long) id)
                .map(userFinder::findByIdOrThrow)
                .orElse(null);
    }
}
