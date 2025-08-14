package io.github.seonrizee.scheduler.global.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 컨트롤러 메서드의 파라미터에 현재 로그인한 사용자 객체를 주입하기 위한 어노테이션.
 * @see io.github.seonrizee.scheduler.global.config.web.LoginUserIdArgumentResolver
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
}
