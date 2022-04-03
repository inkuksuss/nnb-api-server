package com.smartFarm.was.web.config;


import com.smartFarm.was.web.logtrace.LogTrace;
import com.smartFarm.was.web.logtrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalLogTrace();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new setMemberAuthorityInterceptor()).order(1)
//                .addPathPatterns("/board/add", "/board/detail/**", "/board/update/**", "/board/delete/**")
//                .excludePathPatterns("/css/**", "/*.ico", "/error");
//
//        registry.addInterceptor(new addMemberByAuthorityInterceptor()).order(2)
//                .addPathPatterns("/board/add", "/board/detail/**", "/board/update/**", "/board/delete/**")
//                .excludePathPatterns("/css/**", "/*.ico", "/error");
    }
}
