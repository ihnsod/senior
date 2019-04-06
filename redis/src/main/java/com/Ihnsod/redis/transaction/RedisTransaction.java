package com.Ihnsod.redis.transaction;


import com.Ihnsod.redis.common.JedisUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

import java.io.IOException;
import java.util.List;

/**
 * @author: Ihnsod
 * @create: 2018/11/17 23:55
 **/
public class RedisTransaction {

    @Test
    public void test1() throws IOException {
        Jedis jedis = JedisUtils.getJedis();
        jedis.flushDB();

        Pipeline pipelined = jedis.pipelined();

        pipelined.set("me", 1 + "1");
        pipelined.set("you", 1 + "1");

        pipelined.watch("me");
        pipelined.incr("me");

        pipelined.multi();
//        pipelined.incr("me");

        pipelined.incr("you");
        Response<List<Object>> exec = pipelined.exec();

        pipelined.close();

        if (exec != null) {
            List<Object> objects = exec.get();
            if (objects == null || objects.size() == 0) {
                System.err.println("事务执行失败！");
            } else {
                System.err.println(objects);
            }
        }
    }

    @Test
    public void test2() {
        Jedis jedis = JedisUtils.getJedis();
        jedis.flushDB();

        jedis.set("me", "22");
        jedis.watch("me");
        Transaction tx = jedis.multi();
        tx.set("me","23");
        List<Object> exec = tx.exec();
        if (exec == null) {
            System.err.println("事务执行失败！");
        } else {
            System.err.println("事务执行成功！");
        }
    }

    public static void main(String[] args) {
        Jedis jedis = JedisUtils.getJedis();
        String userId = "abc";
        String key = keyFor(userId);
        jedis.setnx(key, String.valueOf(5));
        System.out.println(doubleAccount(jedis, userId));
        jedis.close();
    }

    public static int doubleAccount(Jedis jedis, String userId) {
        String key = keyFor(userId);
        while (true) {
            jedis.watch(key);
            int value = Integer.parseInt(jedis.get(key));
            value *= 2; // 加倍
            Transaction tx = jedis.multi();
            tx.set(key, String.valueOf(value));
            List<Object> res = tx.exec();
            if (res != null) {
                break; // 成功了
            }
        }

        return Integer.parseInt(jedis.get(key)); // 重新获取余额
    }


    public static String keyFor(String userId) {
        return String.format("account_%s", userId);
    }

}