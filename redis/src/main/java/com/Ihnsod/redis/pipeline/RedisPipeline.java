package com.Ihnsod.redis.pipeline;


import com.Ihnsod.redis.common.JedisUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

/**
 * @author: Ihnsod
 * @create: 2018/11/17 22:20
 **/
public class RedisPipeline {

    @Test
    public void test1() {
        Jedis jedis = JedisUtils.getJedis();

        jedis.flushDB();

        Pipeline pi = jedis.pipelined();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            pi.set(i + "key", i + "value");
        }
        pi.sync();
        long end = System.currentTimeMillis();
        System.err.println(end - start);
        jedis.close();

    }

    @Test
    public void test2() {
        Jedis jedis = JedisUtils.getJedis();
        jedis.flushDB();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            jedis.set(i + "", i + "");
        }
        long end = System.currentTimeMillis();
        System.err.println(end - start);
        jedis.close();
    }

    @Test
    public void test3(){
        test1();
        test2();
        Jedis jedis = JedisUtils.getJedis();

        String info = jedis.info();

        System.err.println(info);

        jedis.close();
    }
}
