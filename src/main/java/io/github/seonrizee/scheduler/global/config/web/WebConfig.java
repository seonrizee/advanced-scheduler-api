package io.github.seonrizee.scheduler.global.config.web;

import io.github.seonrizee.scheduler.global.security.filter.SessionFilter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 웹 관련 설정을 구성하는 클래스.
 */
@Configuration
@RequiredArgsConstructor
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class WebConfig implements WebMvcConfigurer {

    private final LoginUserIdArgumentResolver loginUserIdArgumentResolver;

    /**
     * SessionFilter를 등록하고 설정합니다.
     * @param sessionFilter 등록할 세션 필터
     * @return FilterRegistrationBean 객체
     */
    @Bean
    public FilterRegistrationBean<SessionFilter> sessionFilterRegistration(SessionFilter sessionFilter) {
        FilterRegistrationBean<SessionFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(sessionFilter);
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

    /**
     * Pageable 파라미터를 1부터 시작하도록 설정합니다.
     * @return PageableHandlerMethodArgumentResolverCustomizer 객체
     */
    @Bean
    public PageableHandlerMethodArgumentResolverCustomizer customizer() {
        return p -> p.setOneIndexedParameters(true);
    }

    /**
     * 커스텀 Argument Resolver를 등록합니다.
     * @param resolvers HandlerMethodArgumentResolver 리스트
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(loginUserIdArgumentResolver);
    }
}
