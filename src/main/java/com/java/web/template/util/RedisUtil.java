package com.java.web.template.util;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;


@Slf4j
public class RedisUtil {

    private static final Jedis JEDIS;

    static {
        JEDIS = new Jedis("47.95.241.139",6379);
        JEDIS.auth("zhyzhyzhyzhyzhy");
        log.info("redis ping {}",JEDIS.ping());
    }

    public static <T> void set(String key,T value){
        JEDIS.setex(key,120,JsonUtil.objectToJson(value));
    }

    public static <T> void set(String key,Integer second,T value){
        JEDIS.setex(key,second,JsonUtil.objectToJson(value));
    }

    public static <T> T get(String key,Class<T> klass){
        String json=JEDIS.get(key);
        if(json==null){
            log.info("{} miss",key);
            return null;
        }
        log.info("{} hit",key);
        return JsonUtil.jsonToObject(json,klass);
    }
}
