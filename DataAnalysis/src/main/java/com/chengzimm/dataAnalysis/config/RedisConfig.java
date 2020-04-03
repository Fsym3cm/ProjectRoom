package com.chengzimm.dataAnalysis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
@ConditionalOnClass(RedisOperations.class)      //系统中有RedisOperations类时
@EnableConfigurationProperties(RedisProperties.class)  //启动RedisProperties这个类
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    @Autowired
    private RedisTemplate redisTemplate;

    // 配置缓存管理器
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        LettuceConnectionFactory jedisConnectionFactory = (LettuceConnectionFactory) redisTemplate.getConnectionFactory();
        jedisConnectionFactory.setDatabase(2);      //指定dbindex
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        jedisConnectionFactory.resetConnection();

        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(60*20)) // 20分钟缓存失效
                // 设置key的序列化方式
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                // 设置value的序列化方式
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new FastJsonRedisSerializer(Object.class)))
                // 不缓存null值
                .disableCachingNullValues();
        RedisCacheManager redisCacheManager = RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(config)
                .transactionAware()
                .build();
        return redisCacheManager;
    }
}
