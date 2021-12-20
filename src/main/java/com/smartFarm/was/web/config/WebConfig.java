package com.smartFarm.was.web.config;


import com.smartFarm.was.domain.v2.OrderControllerV2;
import com.smartFarm.was.domain.v2.OrderRepositoryV2;
import com.smartFarm.was.domain.v2.OrderServiceV2;
import com.smartFarm.was.web.config.filter.LogFilter;
import com.smartFarm.was.web.logtrace.LogTrace;
import com.smartFarm.was.web.logtrace.ThreadLocalLogTrace;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.servlet.Filter;
import javax.sql.DataSource;

@Configuration
public class WebConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactoryBean.setConfigLocation(resolver.getResource("classpath:mybatis/mybatis-config.xml"));
        sessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mybatis/mapper/*.xml"));
        sessionFactoryBean.setTypeAliasesPackage("com.smartFarm.was.domain.model");
        sessionFactoryBean.setTypeAliasesPackage("com.smartFarm.was.web.dto");

        return sessionFactoryBean.getObject();
    }

    @Bean
    public FilterRegistrationBean logFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LogFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/*");

        return filterRegistrationBean;
    }

    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalLogTrace();
    }
}
