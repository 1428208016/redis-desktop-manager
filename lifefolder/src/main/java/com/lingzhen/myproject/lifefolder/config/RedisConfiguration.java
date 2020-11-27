package com.lingzhen.myproject.lifefolder.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@Configuration
public class RedisConfiguration {

    @Value("${spring.redis.host}")
    private String HOST;
    @Value("${spring.redis.port}")
    private int PORT;
    @Value("${spring.redis.password}")
    private String PASSWORD;

    /**
     * 连接工厂
     * @return
     */
    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(HOST, PORT);
        config.setDatabase(0);
        config.setPassword(PASSWORD);
        return new JedisConnectionFactory(config);
    }

    /**
     * 操作模板
     * @param redisConnectionFactory
     * @return
     */
    @Bean(name = "myRedisTemplate")
    public RedisTemplate myRedisTemplate(JedisConnectionFactory redisConnectionFactory){
        RedisTemplate template = new RedisTemplate();
        // 连接工程
        template.setConnectionFactory(redisConnectionFactory);
        // 格式化
        template.setDefaultSerializer(new GenericToStringSerializer<>(String.class));
        return template;
    }

}
