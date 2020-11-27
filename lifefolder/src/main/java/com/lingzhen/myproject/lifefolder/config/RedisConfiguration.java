package com.lingzhen.myproject.lifefolder.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@Configuration
public class RedisConfiguration {

    @Value("${spring.redis.host}")
    private String HOST;
    @Value("${spring.redis.port}")
    private int PORT;
    @Value("${spring.redis.password}")
    private String PASSWORD;

    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(HOST, PORT);
        config.setDatabase(0);
        config.setPassword(PASSWORD);
        return new JedisConnectionFactory(config);
    }

}
