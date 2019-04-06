package com.Ihnsod.redis.utils;

import redis.clients.jedis.*;
import redis.clients.util.Slowlog;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * jedis接口封装所有jedis方法
 */
public interface JedisClient {

    Long append(String key, String value);

    String auth(String password);

    List<String> blpop(int timeout, String... keys);

    List<String> brpop(int timeout, String... keys);

    String brpoplpush(String source, String destination, int timeout);

    List<String> configGet(String pattern);

    String configSet(String parameter, String value);

    Long decr(String key);

    Long decrBy(String key, long integer);

    Long del(String... keys);

    String echo(String string);

    Object eval(String script);

    Object eval(String script, int keyCount, String... params);

    Object eval(String script, List<String> keys, List<String> args);

    Object evalsha(String script);

    Object evalsha(String sha1, int keyCount, String... params);

    Object evalsha(String sha1, List<String> keys, List<String> args);

    Boolean exists(String key);

    Long expire(String key, int seconds);

    Long expireAt(String key, long unixTime);

    String flushAll();

    String flushDB();

    String get(String key);

    Boolean getbit(String key, long offset);

    String getrange(String key, long startOffset, long endOffset);

    String getSet(String key, String value);

    Long hdel(String key, String... fields);

    Boolean hexists(String key, String field);

    String hget(String key, String field);

    Map<String, String> hgetAll(String key);

    Long hincrBy(String key, String field, long value);

    Set<String> hkeys(String key);

    Long hlen(String key);

    List<String> hmget(String key, String... fields);

    String hmset(String key, Map<String, String> hash);

    Long hset(String key, String field, String value);

    Long hsetnx(String key, String field, String value);

    List<String> hvals(String key);

    Long incr(String key);

    Long incrBy(String key, long integer);

    Set<String> keys(String pattern);

    String lindex(String key, long index);

    Long linsert(String key, BinaryClient.LIST_POSITION where, String pivot, String value);

    Long llen(String key);

    String lpop(String key);

    Long lpush(String key, String... strings);

    Long lpushx(String key, String string);

    List<String> lrange(String key, long start, long end);

    Long lrem(String key, long count, String value);

    String lset(String key, long index, String value);

    String ltrim(String key, long start, long end);

    List<String> mget(String... keys);

    Long move(String key, int dbIndex);

    String mset(String... keysvalues);

    Long msetnx(String... keysvalues);

    String objectEncoding(String string);

    Long objectIdletime(String string);

    Long objectRefcount(String string);

    Long persist(String key);

    String ping();

    void psubscribe(JedisPubSub jedisPubSub, String... patterns);

    Long publish(String channel, String message);

    String quit();

    String randomKey();

    String rename(String oldkey, String newkey);

    Long renamenx(String oldkey, String newkey);

    String rpop(String key);

    String rpoplpush(String srckey, String dstkey);

    Long rpush(String key, String... strings);

    Long rpushx(String key, String string);

    Long sadd(String key, String... members);

    Long scard(String key);

    List<Boolean> scriptExists(String... sha1);

    Boolean scriptExists(String sha1);

    String scriptLoad(String script);

    Set<String> sdiff(String... keys);

    Long sdiffstore(String dstkey, String... keys);

    String select(int index);

    String set(String key, String value);

    Boolean setbit(String key, long offset, boolean value);

    String setex(String key, int seconds, String value);

    Long setnx(String key, String value);

    Long setrange(String key, long offset, String value);

    Set<String> sinter(String... keys);

    Long sinterstore(String dstkey, String... keys);

    Boolean sismember(String key, String member);

    List<Slowlog> slowlogGet();

    List<Slowlog> slowlogGet(long entries);

    Set<String> smembers(String key);

    Long smove(String srckey, String dstkey, String member);

    List<String> sort(String key);

    List<String> sort(String key, SortingParams sortingParameters);

    Long sort(String key, SortingParams sortingParameters, String dstkey);

    Long sort(String key, String dstkey);

    String spop(String key);

    String srandmember(String key);

    Long srem(String key, String... members);

    Long strlen(String key);

    void subscribe(JedisPubSub jedisPubSub, String... channels);

    String substr(String key, int start, int end);

    Set<String> sunion(String... keys);

    Long sunionstore(String dstkey, String... keys);

    Long ttl(String key);

    String type(String key);

    String watch(String... keys);

    Long zadd(String key, double score, String member);

    Long zadd(String key, Map<Double, String> scoreMembers);

    Long zcard(String key);

    Long zcount(String key, double min, double max);

    Long zcount(String key, String min, String max);

    Double zincrby(String key, double score, String member);

    Long zinterstore(String dstkey, String... sets);

    Long zinterstore(String dstkey, ZParams params, String... sets);

    Set<String> zrange(String key, long start, long end);

    Set<String> zrangeByScore(String key, double min, double max);

    Set<String> zrangeByScore(String key, double min, double max, int offset, int count);

    Set<String> zrangeByScore(String key, String min, String max);

    Set<String> zrangeByScore(String key, String min, String max, int offset, int count);

    Set<Tuple> zrangeByScoreWithScores(String key, double min, double max);

    Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count);

    Set<Tuple> zrangeByScoreWithScores(String key, String min, String max);

    Set<Tuple> zrangeByScoreWithScores(String key, String min, String max, int offset, int count);

    Set<Tuple> zrangeWithScores(String key, long start, long end);

    Long zrank(String key, String member);

    Long zrem(String key, String... members);

    Long zremrangeByRank(String key, long start, long end);

    Long zremrangeByScore(String key, double start, double end);

    Long zremrangeByScore(String key, String start, String end);

    Set<String> zrevrange(String key, long start, long end);

    Set<String> zrevrangeByScore(String key, double max, double min);

    Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count);

    Set<String> zrevrangeByScore(String key, String max, String min);

    Set<String> zrevrangeByScore(String key, String max, String min, int offset, int count);

    Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min);

    Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count);

    Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min);

    Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min, int offset, int count);

    Set<Tuple> zrevrangeWithScores(String key, long start, long end);

    Long zrevrank(String key, String member);

    Double zscore(String key, String member);

    Long zunionstore(String dstkey, String... sets);

    Long zunionstore(String dstkey, ZParams params, String... sets);
}