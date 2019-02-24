package com.zxx.senior.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class JedisConfig {

    @Bean
    public JedisPool jedisPool(@Autowired@Qualifier("redis.config") JedisPoolConfig config,
                               @Value("${redis.host}") String host,
                               @Value("${redis.port}") int port) {
        return new JedisPool(config, host, port);
    }

    @Bean(name = "redis.config")
    public JedisPoolConfig jedisPoolConfig(@Value("${redis.poolMaxTotal}") int maxTotal,
                                           @Value("${redis.poolMaxIdle}") int maxIdle,
                                           @Value("${redis.poolMaxWait}") int maxWaitMillis) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        return config;
    }
}
