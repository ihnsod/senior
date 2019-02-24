package com.zxx.senior.redis.string;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zxx.senior.redis.common.JedisUtils;
import com.zxx.senior.redis.pojo.User;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @author: Aries
 * @create: 2018/11/11 17:27
 **/
public class RedisString {

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void test1() {
        Jedis jedis = JedisUtils.getJedis();
        jedis.set("hello", "world");

        System.err.println(jedis.get("hello"));
        System.err.println(jedis.get("world"));


    }

    @Test
    public void test2() throws JsonProcessingException {
        Jedis jedis = JedisUtils.getJedis();

        User user = new User();

        user.setId(1);
        user.setName("hello");
        user.setAddress("world");

        String value = mapper.writeValueAsString(user);
        jedis.set("user", value);

        System.err.println(jedis.get("user"));
    }

    @Test
    public void test3() {
        Jedis jedis = JedisUtils.getJedis();
        //批量字符串操作
        jedis.mset("key1", "value1", "key2", "value2", "key3", "value3");

        //返回的是一个List列表
        System.err.println(jedis.mget("key1", "key2"));
    }

    @Test
    public void test4() {
        Jedis jedis = JedisUtils.getJedis();
        //设置过期时间  十秒后删除
        jedis.setex("key4", 10, "value4");

        //如果key不存在就创建 否则不进行操作
        System.err.println(jedis.setnx("key5", "value5"));
        System.err.println(jedis.setnx("key5", "value5"));

    }

    @Test
    public void test5() {
        Jedis jedis = JedisUtils.getJedis();
        // 如果value是个Integer类型的可以进行计数
        jedis.set("key6", "1");

        jedis.incr("key6");

        System.err.println(jedis.get("key6"));

    }
}
