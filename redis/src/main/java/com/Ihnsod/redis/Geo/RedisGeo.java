package com.Ihnsod.redis.Geo;


import com.Ihnsod.redis.common.JedisUtils;
import org.junit.Test;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.geo.GeoRadiusParam;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author: Ihnsod
 * @create: 2018/11/14 23:08
 **/
public class RedisGeo {

    /**
     * redis中的geohash算法可以实现附近的人的功能
     * 本质上是zset 结构 所以geo没有删除指令，可以世界使用zset的删除指令进行删除
     */
    @Test
    public void test(){
        Jedis jedis = JedisUtils.getJedis();
        jedis.flushDB();

        // 参1 key  参2 经度  参3  维度  参4  value
        jedis.geoadd("company",116.48105,39.996794,"juejin");
        jedis.geoadd("company",116.514203,39.905409,"ireader");
        jedis.geoadd("company",116.489033,40.007669,"meituan");
        jedis.geoadd("company",116.562108,39.787602,"jd");
        jedis.geoadd("company",116.334255,40.027400,"xiaomi");

        //geodist指令可以计算两个元素之间的距离
        Double geodist = jedis.geodist("company", "juejin", "jd", GeoUnit.KM);
        System.err.println(geodist);


        // geopos指令可以获取集合中任意元素的经纬度坐标，可以一次获取多个。
        //果果指定的元素不存在 则集合中会存在Null元素的情况 注意npc 异常
        List<GeoCoordinate> geopos = jedis.geopos("company", "juejin", "xiaomi", "meituan");

        List<GeoCoordinate> collect = geopos.stream().filter(geoCoordinate -> geoCoordinate != null).collect(Collectors.toList());

        System.err.println(collect.size());

        System.err.println(geopos.size());

        List<String> geohash = jedis.geohash("company", "juejin");

        //获取成员的hash值
        System.err.println(geohash);

        //实现某个value附近的人功能 可以指定范围 排序方式
        List<GeoRadiusResponse> responses = jedis.georadiusByMember("company", "juejin", 50, GeoUnit.KM, GeoRadiusParam.geoRadiusParam().sortDescending());

        responses.forEach(g ->{
            System.err.println(g.getMemberByString());
        });

        //根据传入的坐标值查询附近的东西

        List<GeoRadiusResponse> company = jedis.georadius("company", 116.514202, 39.905409, 20, GeoUnit.KM);

        List<String> list = Optional.ofNullable(company.stream().map(geo -> geo.getMemberByString()).collect(Collectors.toList())).orElse(Collections.emptyList());

        System.err.println(list);

    }
}
