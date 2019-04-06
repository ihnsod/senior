package com.Ihnsod.redis.hash;


import com.Ihnsod.redis.common.JedisUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @author: Ihnsod
 * @create: 2018/11/11 20:33
 **/
public class RedisHash {

    @Test
    public void test1(){
        Jedis jedis = JedisUtils.getJedis();
        jedis.flushDB();

        jedis.hset("k1","f1","v1");

        System.err.println(jedis.hget("k1","f1"));

        System.err.println(jedis.hget("k1","f2"));

        //key所有的value  返回的值为Map<String,String> 结构 key 为 filed ，value 为 value
        System.err.println(jedis.hgetAll("k1"));

        jedis.hset("k2","f2","1");

        //给hash 字典进行计数
        jedis.hincrBy("k2","f2",2);

        System.err.println(jedis.hget("k2","f2"));
    }
}
