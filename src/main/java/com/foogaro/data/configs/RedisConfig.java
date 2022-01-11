package com.foogaro.data.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.util.Optional;

@Configuration
@EnableRedisRepositories(basePackages = "com.foogaro.data.repositories")
public class RedisConfig {

    public static final String REDIS_DEFAULT_HOST = "127.0.0.1";
    public static final String REDIS_DEFAULT_PORT = "6379";

    @Bean
    public RedisStandaloneConfiguration redisStandaloneConfiguration() {
        String host = Optional.ofNullable(System.getenv("spring.redis.host")).orElse(REDIS_DEFAULT_HOST);
        String port = Optional.ofNullable(System.getenv("spring.redis.port")).orElse(REDIS_DEFAULT_PORT);
        String password = Optional.ofNullable(System.getenv("spring.redis.password")).orElse("");

        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setPort(Integer.parseInt(port));
        redisStandaloneConfiguration.setPassword(password);
        return redisStandaloneConfiguration;
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new JedisConnectionFactory(redisStandaloneConfiguration());
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(redisConnectionFactory());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        return template;
    }

}
