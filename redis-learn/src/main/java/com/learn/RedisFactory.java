package com.learn;

import redis.clients.jedis.JedisPool;

import java.net.URI;

public class RedisFactory {
    private static JedisPool jedisPool = null;

    private RedisFactory(){

    }

    public static JedisPool getInstance(){
        if(jedisPool == null){
            synchronized (RedisFactory.class){
                if(jedisPool == null){
                    JedisPoolConfig config = new JedisPoolConfig();
                    config.setMaxTotal(8);
                    config.setMaxIdle(8);
                    config.setMinIdle(8);

                    config.setTestOnBorrow(true);
                    config.setTestOnReturn(true);
                    config.setTestWhileIdle(true);

                    config.setMaxWaitMillis(3000);
                    config.setMinEvictableIdleTimeMillis(60);
                    config.setTimeBetweenEvictionRunsMillis(30);
                    config.setBlockWhenExhausted(false);
                    URI uri = URI.create("redis://192.168.8.100:6379");

                    jedisPool = new JedisPool(config, uri, 2000, 2000);
                }
            }
        }

        return jedisPool;
    }
}
