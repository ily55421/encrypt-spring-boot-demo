package com.link.encrypt.common.config;

import com.link.encrypt.common.secret.SecretFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;

import static com.link.encrypt.common.constants.SecretConstant.PREFIX;

/**
 * @author lin 2022/12/9 22:52
 */
@Configuration
public class WebConfig {
    @Bean
    public Filter secretFilter() {
        return new SecretFilter();
    }

    /**
     * 注册过滤器bean
     *
     * @return FilterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean filterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new DelegatingFilterProxy("secretFilter"));
        registration.setName("secretFilter");
        registration.addUrlPatterns(PREFIX + "/*");
        registration.setOrder(1);
        return registration;
    }
}