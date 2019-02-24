package com.zxx.senior.redis.list;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zxx.senior.redis.common.JedisUtils;
import com.zxx.senior.redis.pojo.User;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Aries
 * @create: 2018/11/11 17:39
 **/
public class RedisList {

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void test1() {
        Jedis jedis = JedisUtils.getJedis();

        jedis.flushDB();
        //链表的操作
        jedis.rpush("k1", "v1", "v2", "v3", "v4");

        //获取列表的长度
        System.err.println(jedis.llen("k1"));

        //左边出 相当于队列
        System.err.println(jedis.lpop("k1"));
        //再次获取list列表长度为3
        System.err.println(jedis.llen("k1"));
        //右边出 相当于栈
        System.err.println(jedis.rpop("k1"));
        //再次获取list列表长度为2
        System.err.println(jedis.llen("k1"));
    }

    @Test
    public void test2() {
        Jedis jedis = JedisUtils.getJedis();

        jedis.flushDB();
        //链表的操作
        jedis.rpush("k1", "v1", "v2", "v3", "v4");

        //根据索引找到值 时间复杂度为O(n)
        System.err.println(jedis.lindex("k1",2));

        //遍历整个链表  时间复杂度为O(n)
        System.err.println(jedis.lrange("k1",0,-1));

        // 保留数据  把索引下标 1 到 3之间的元素保留 索引从0开始 包含右侧的索引为对应的数据
        System.err.println(jedis.ltrim("k1",1,3));

        System.err.println(jedis.llen("k1"));

        System.err.println(jedis.lrange("k1",0,-1));

    }

    @Test
    public void test3() throws IOException {
        Jedis jedis = JedisUtils.getJedis();
        jedis.flushDB();

        List<User> users =  new ArrayList<>();

        User user = new User();
        user.setId(1);
        user.setName("k");
        user.setAddress("me");

        User user1 = new User();
        user1.setAddress("you");
        user1.setName("v");
        user1.setId(2);

        users.add(user);
        users.add(user1);

        String s = mapper.writeValueAsString(users);

        System.err.println(s);

        List list = mapper.readValue(s, List.class);

        System.err.println(list);

    }

}
