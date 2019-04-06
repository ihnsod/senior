package com.Ihnsod.redis.scoreset;


import com.Ihnsod.redis.common.JedisUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.Set;

/**
 * @author: Ihnsod
 * @create: 2018/11/11 21:14
 **/
public class RedisScoreSet {

    @Test
    public void test1(){
        Jedis jedis = JedisUtils.getJedis();
        jedis.flushDB();

        jedis.zadd("k",100,"v1");
        jedis.zadd("k",99.888,"v2");
        jedis.zadd("k",67.02,"v3");
        jedis.zadd("k",88,"v4");

        //按照score  排序输出 从小到大
        System.err.println(jedis.zrange("k",0,-1));

        //按照score 逆序排序输出 从大到小
        System.err.println(jedis.zrevrange("k",0,1));

        //获取key的数量
        System.err.println(jedis.zcard("k"));

        //获取指定value 的 score
        System.err.println(jedis.zscore("k","v1"));

        //根据分值区间 遍历zset
        System.err.println(jedis.zrangeByScore("k",88,99.9));

        //获取分值区间  获取value值和分数
        Set<Tuple> k = jedis.zrangeByScoreWithScores("k", 88, 99.9);

        k.forEach(tuple -> {
            System.err.println(tuple.getElement()+":"+tuple.getScore());
        });

        //删除指定的value
        jedis.zrem("k","v1");

        System.err.println(jedis.zcard("k"));

        jedis.del("k");

        System.err.println(jedis.zcard("k"));
    }
}
