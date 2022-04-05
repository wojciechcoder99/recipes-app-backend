package com.courseapp.backend;

import com.courseapp.backend.filters.HeadersFilter;
import com.courseapp.backend.interceptors.AddingHeadersInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Set;

@Configuration
public class Config implements WebMvcConfigurer {
    private Set<HandlerInterceptor> interceptors;

    public Config(Set<HandlerInterceptor> interceptors) {
        this.interceptors = interceptors;
    }

    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(false);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        interceptors.forEach(registry::addInterceptor);
    }

    @Bean
    public FilterRegistrationBean<HeadersFilter> headersFilter() {
        FilterRegistrationBean<HeadersFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new HeadersFilter());
        registrationBean.addUrlPatterns("/api/recipes");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
