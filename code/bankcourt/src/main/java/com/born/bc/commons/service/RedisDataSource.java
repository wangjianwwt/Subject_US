package com.born.bc.commons.service;

import redis.clients.jedis.ShardedJedis;

/**
 * RedisDataSource
 * @author wangjian
 *
 */
public interface RedisDataSource {
    public abstract ShardedJedis getRedisClient();
    public void returnResource(ShardedJedis shardedJedis);
    public void returnResource(ShardedJedis shardedJedis,boolean broken);
}