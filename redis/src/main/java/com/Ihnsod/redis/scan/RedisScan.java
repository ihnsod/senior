package com.Ihnsod.redis.scan;


import com.Ihnsod.redis.common.JedisUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Ihnsod
 * @create: 2018/11/17 16:36
 **/
public class RedisScan {

    @Test
    public void test1() {
        Jedis jedis = JedisUtils.getJedis();
        jedis.flushDB();

        for (int i = 0; i < 100; i++) {
            jedis.set("key" + i, i + "");
        }
        ScanParams params = new ScanParams();
        params.match("key9*");
        //每次查询十个
        params.count(10);

        String cursor = "0";
        List<String> list = new ArrayList<>();
        do {
            ScanResult<String> scan = jedis.scan(cursor, params);
            //把游标值设置为新的
            cursor = scan.getStringCursor();
            list.addAll(scan.getResult());
        } while (!cursor.equals("0"));


        System.err.println(list);
        System.err.println(list.size());
        System.err.println(list.stream().distinct().collect(Collectors.toList()).size());

    }
}
