package com.lingzhen.myproject.config.redis;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPoolConfig;


/**
 * @author qianhaoliang
 * @date 2018/7/11 12:14
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Configuration
@EnableCaching
public class RedisConfig {


    //master配置
    @Value("${spring.redis.master.database}")
    private int master_dbIndex;

    @Value("${spring.redis.master.host}")
    private String master_host;

    @Value("${spring.redis.master.password}")
    private String master_password;

    @Value("${spring.redis.master.port}")
    private int master_port;

    @Value("${spring.redis.master.timeout}")
    private int master_timeout;



    @Value("${spring.redis.pool.max-active}")
    private int redisPoolMaxActive;
    @Value("${spring.redis.pool.max-idle}")
    private int redisPoolMaxIdle;
    @Value("${spring.redis.pool.min-idle}")
    private int redisPoolMinIdle;
    @Value("${spring.redis.pool.max-wait}")
    private int redisPoolMaxWait;
    @Value("${spring.redis.pool.testOnBorrow}")
    private boolean testOnBorrow;


    //master
 
	@Bean(name = "redisTemplateMaster")
    public RedisTemplate redisTemplateMaster() {
        RedisTemplate redisTemplateMaster = new RedisTemplate();
        redisTemplateMaster.setConnectionFactory(connectionFactory(master_host, master_port, master_password,
                redisPoolMaxIdle, redisPoolMaxActive, master_dbIndex, redisPoolMaxWait, testOnBorrow));
        //设置序列化key的实例化对象
        redisTemplateMaster.setKeySerializer(new StringRedisSerializer());
        //设置序列化value的实例化对象
//        redisTemplateMaster.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplateMaster.setValueSerializer(new JdkSerializationRedisSerializer());

        redisTemplateMaster.setHashKeySerializer(new StringRedisSerializer());

        redisTemplateMaster.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        return redisTemplateMaster;
    }






    @SuppressWarnings("deprecation")
	public RedisConnectionFactory connectionFactory(String hostName, int port,
                                                    String password, int maxIdle, int maxTotal, int index,
                                                    long maxWaitMillis, boolean testOnBorrow) {
        JedisConnectionFactory jedis = new JedisConnectionFactory();
        
        jedis.setHostName(hostName);
        jedis.setPort(port);
        if (!StringUtils.isEmpty(password)) {
            jedis.setPassword(password);
        }
        if (index != 0) {
            jedis.setDatabase(index);
        }
        jedis.setPoolConfig(poolCofig(maxIdle, maxTotal, maxWaitMillis,
                testOnBorrow));
        // 初始化连接pool
        jedis.afterPropertiesSet();
        RedisConnectionFactory factory = jedis;

        return factory;
    }

    public JedisPoolConfig poolCofig(int maxIdle, int maxTotal,
                                     long maxWaitMillis, boolean testOnBorrow) {
        JedisPoolConfig poolCofig = new JedisPoolConfig();
        poolCofig.setMaxIdle(maxIdle);
        poolCofig.setMaxTotal(maxTotal);
        poolCofig.setMaxWaitMillis(maxWaitMillis);
        poolCofig.setTestOnBorrow(testOnBorrow);
        return poolCofig;
    }
}



