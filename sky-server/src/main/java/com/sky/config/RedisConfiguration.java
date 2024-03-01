package com.sky.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.validation.constraints.Size;

/**
 * @Author：yep
 * @Project：sky-take-out
 * @name：RedisConfiguration
 * @Date：2024/2/29 20:17
 * @Filename：RedisConfiguration
 */
@Configuration
@Slf4j
public class RedisConfiguration {
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory){
        log.info("配置redis模板对象");
        RedisTemplate redisTemplate = new RedisTemplate();

        //设置redis的连接工厂对象

        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //设置redis，key的序列化器（防止在进行key的添加的时候的乱码）
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
