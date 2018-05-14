package com.eddie.user.redis;

import com.common.Pool.RedisClient;
import com.common.Pool.RedisFactory;

import java.util.concurrent.TimeUnit;

public class RedisUtil {

    private RedisClient redisClient = RedisFactory.build("localhost",6379);

    public  <T> T get(String key, Class cls){
        return redisClient.getData(key,cls);
    }

    public void set(String key,Object value){
        redisClient.putData(key,value);
    }

    public void set(String key,Object value, int timeout){
        redisClient.putData(key, timeout, value);
    }

    public void expire(String key, int timeout){
        redisClient.expire(key,timeout);
    }
}
