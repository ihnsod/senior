package com.Ihnsod.redis.application;


import com.Ihnsod.redis.common.JedisUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import java.io.IOException;

/**
 * @author: Ihnsod
 * @create: 2018/11/12 00:13
 **/
public class LimitStream {

    //限流应用
    private static boolean isActionAllowed(String userId, String actionKey, int period, int maxCount, Jedis jedis) throws IOException {
        String key = String.format("hist:%s:%s", userId, actionKey);
        //获取当前系统的时间
        long nowTs = System.currentTimeMillis();
        //开启 jedis管道
        Pipeline pipe = jedis.pipelined();
        //开启
        pipe.multi();
        //把此刻的值加入 scoreset中
        pipe.zadd(key, nowTs, "" + nowTs);
        //移除有续集中指定分数内的所有元素
        pipe.zremrangeByScore(key, 0, nowTs - period * 1000);
        //获取数量
        Response<Long> count = pipe.zcard(key);
        //设置过期时间
        pipe.expire(key, period + 1);
        //管道提交
        pipe.exec();
        //管道关闭
        pipe.close();
        //判断当前请求的数量是否达到了限流的最大数量
        return count.get() <= maxCount;
    }

    public static void main(String[] args) throws IOException {

        Jedis jedis = JedisUtils.getJedis();
        for (int i = 0; i < 100; i++) {
            System.out.println(isActionAllowed("laoqian", "reply", 60, 5, jedis));
        }

    }
}
