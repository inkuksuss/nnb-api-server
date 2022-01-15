package com.smartFarm.was.web.config.database;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import javax.sql.DataSource;
import java.sql.Connection;


@Configuration
@Slf4j
@EnableRedisHttpSession
@RequiredArgsConstructor
public class DBConfig implements ApplicationRunner {

    private final DataSource dataSource;
    private final RedisProperties redisProperties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try (Connection connection = dataSource.getConnection()){
            String url = connection.getMetaData().getURL();
            String userName = connection.getMetaData().getUserName();
            log.info("url = " + url);
            log.info("connection = " + connection);
            log.info("userName = " + userName);
        }
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactoryBean.setConfigLocation(resolver.getResource("classpath:mybatis/mybatis-config.xml"));
        sessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mybatis/mapper/*.xml"));
        sessionFactoryBean.setTypeAliasesPackage("com.smartFarm.was.domain.model");
        sessionFactoryBean.setTypeAliasesPackage("com.smartFarm.was.domain.dto");

        return sessionFactoryBean.getObject();
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(redisProperties.getHost(), redisProperties.getPort());
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
