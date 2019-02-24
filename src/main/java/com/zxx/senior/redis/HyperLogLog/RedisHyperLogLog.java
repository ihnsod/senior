package com.zxx.senior.redis.HyperLogLog;

import com.zxx.senior.redis.common.JedisUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @author: Aries
 * @create: 2018/11/11 22:45
 **/
public class RedisHyperLogLog {

    @Test
    public void test1(){
        Jedis jedis = JedisUtils.getJedis();
        jedis.flushDB();
        // redis 中的pfadd 命令可以用来统计要求不怎么准确的数据 误差率在0.5%

        for (int i = 0; i < 1000000; i++) {
            jedis.pfadd("k",i+"");
        }

        long k = jedis.pfcount("k");

        System.err.println(k);

    }
}
