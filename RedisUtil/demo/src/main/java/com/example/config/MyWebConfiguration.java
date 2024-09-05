package com.example.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Sky
 * create 2019/03/20
 * email sky.li@ixiaoshuidi.com
 **/
@Configuration
public class MyWebConfiguration extends WebMvcConfigurerAdapter {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestInterceptor()).addPathPatterns("/wrapper/request");
        registry.addInterceptor(new AuthenticationInterceptor()).addPathPatterns("/jwt/verifyToken");
        super.addInterceptors(registry);
    }


    @Bean
    public FilterRegistrationBean bodyReaderFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new BodyReaderFilter());
        registration.addUrlPatterns("/wrapper/request");
        registration.setName("bodyReaderFilter");
        return registration;
    }


}
