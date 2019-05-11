package com.hex.code.order.config;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2019-05-07.20:44
 */

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;


@Configuration
public class RedisConfig {

    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private int port;
    @Value("${redis.lettuce.timeout}")
    private int timeout;
    @Value("${redis.lettuce.pool.max-idle}")
    private int maxIdle;
    @Value("${redis.lettuce.pool.max-wait}")
    private long maxWaitMillis;
    @Value("${redis.password}")
    private String password;
    @Value("${redis.database}")
    private int index;

//    @Bean
//    public KeyGenerator redisGenerator() {
//        return new KeyGenerator() {
//            @Override
//            public Object generate(Object target, Method method, Object... params) {
//                StringBuilder sb = new StringBuilder();
//                sb.append(target.getClass().getName());
//                sb.append(method.getName());
//                for (Object obj : params) {
//                    sb.append(obj.toString());
//                }
//                return sb.toString();
//            }
//        };
//    }

    //    //缓存管理器
//    @Bean
//    public RedisCacheManager cacheManager(JedisConnectionFactory jedisConnectionFactory) {
//        return RedisCacheManager.create(jedisConnectionFactory);
//    }

    //    //缓存管理器
//    @Bean
//    public RedisCacheManager cacheManager(LettuceConnectionFactory lettuceConnectionFactory) {
//        return RedisCacheManager.create(lettuceConnectionFactory);
//    }


    @Bean
    RedisStandaloneConfiguration getConfig() {

        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(host);
        config.setPort(port);
        config.setPassword(RedisPassword.of(password));
        config.setDatabase(index);
        return config;

    }

    @Bean
    GenericObjectPoolConfig getPoolConfig() {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
//        poolConfig.setMaxTotal();
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMaxWaitMillis(maxWaitMillis);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnCreate(true);
        poolConfig.setTestWhileIdle(true);
        return poolConfig;
    }


    private RedisTemplate<String, Object> getRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplateObject = new RedisTemplate();
        redisTemplateObject.setConnectionFactory(factory);

        // 使用Jackson2JsonRedisSerialize 替换默认序列化
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // 设置value的序列化规则和 key的序列化规则
        redisTemplateObject.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplateObject.setKeySerializer(new StringRedisSerializer());
        redisTemplateObject.afterPropertiesSet();
        return redisTemplateObject;
    }


    @Bean
    public LettuceConnectionFactory ConnectionFactory(RedisStandaloneConfiguration config, GenericObjectPoolConfig poolConfig) {
        LettucePoolingClientConfiguration pool = LettucePoolingClientConfiguration.builder()
                .poolConfig(poolConfig)
                .commandTimeout(Duration.ofMillis(timeout))
//                .shutdownTimeout(Duration.ofMillis(1))
                .build();
        //其他配置，可再次扩展
        LettuceConnectionFactory factory = new LettuceConnectionFactory(config, pool);
//        factory.setShareNativeConnection(false);
        //是否允许多个线程操作共用同一个缓存连接，默认true，false时每个操作都将开辟新的连接
        return factory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory factory) {
        return getRedisTemplate(factory);
    }
}


