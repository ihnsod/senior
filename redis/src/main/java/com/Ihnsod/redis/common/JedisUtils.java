package com.Ihnsod.redis.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author: Ihnsod
 * @create: 2018/11/17 16:28
 **/
public class JedisUtils {

    private static JedisPool jedisPool = null;

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(50);
        config.setMaxIdle(5);
        config.setMaxWaitMillis(1000 * 100);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);
        jedisPool = new JedisPool(config, "127.0.0.1", 6379, 2000);

    }

    public static Jedis getJedis() {
        return jedisPool.getResource();
    }
}
