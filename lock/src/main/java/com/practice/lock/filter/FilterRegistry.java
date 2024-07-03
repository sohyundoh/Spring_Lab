package com.practice.lock.filter;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FilterRegistry {
    private static final Integer FIRST = 1;

    @Bean
    public FilterRegistrationBean<Filter> customServletWrappingFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new CustomServletWrappingFilter());
        filterRegistrationBean.setOrder(FIRST);
        return filterRegistrationBean;
    }
}
