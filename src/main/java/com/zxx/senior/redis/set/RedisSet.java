package com.zxx.senior.redis.set;


import com.zxx.senior.redis.common.JedisUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @author: Aries
 * @create: 2018/11/11 20:51
 **/
public class RedisSet {

    @Test
    public void test1(){
        Jedis jedis = JedisUtils.getJedis();
        jedis.flushDB();

        //set集合 数据结构和hashSet相同 所有的key的value = null
        //同样是无序的
        jedis.sadd("k","v1","v2","v3","v4","v2");

        System.err.println(jedis.smembers("k"));

        //查找某个值是否存在  参1 为key 参2 为value
        Boolean sismember = jedis.sismember("k", "v1");

        System.err.println(sismember);

        //获取长度
        System.err.println(jedis.scard("k"));

        ////弹出一个  被弹出的元素是随机的
        System.err.println(jedis.spop("k"));

        System.err.println(jedis.scard("k"));
    }
}
