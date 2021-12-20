package com.smartFarm.was.web.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.time.Duration;

@Component
@Slf4j
public class DBConfig implements ApplicationRunner {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

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

    public String getData(String key){
        ValueOperations<String,String> valueOperations = stringRedisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    public void setData(String key, String value){
        ValueOperations<String,String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set(key,value);
    }

    public void setDataExpire(String key,String value,long duration){
        ValueOperations<String,String> valueOperations = stringRedisTemplate.opsForValue();
        Duration expireDuration = Duration.ofSeconds(duration);
        valueOperations.set(key,value,expireDuration);
    }

    public void deleteData(String key){
        stringRedisTemplate.delete(key);
    }
}
