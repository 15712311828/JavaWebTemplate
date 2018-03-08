package com.java.web.template.util;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;


@Slf4j
public class RedisUtil {

    private static Jedis JEDIS;

    static {
        JEDIS = new Jedis("47.95.241.139",6379);
        JEDIS.auth("zhyzhyzhyzhyzhy");
        log.info("redis ping {}",JEDIS.ping());
    }

    private static void reconnect(){
        JEDIS = new Jedis("47.95.241.139",6379);
        JEDIS.auth("zhyzhyzhyzhyzhy");
        log.info("redis ping {}",JEDIS.ping());
    }

    public static <T> void set(String key,T value){
        try {
            JEDIS.setex(key, 120, JsonUtil.objectToJson(value));
        }
        catch(JedisConnectionException e){
            reconnect();
        }
    }

    public static <T> void set(String key,Integer second,T value){
        try {
            JEDIS.setex(key, second, JsonUtil.objectToJson(value));
        }
        catch(JedisConnectionException e){
            reconnect();
        }
    }

    public static <T> T get(String key,Class<T> klass){
        String json=null;
        try {
            json = JEDIS.get(key);
        }
        catch(Exception e){
            log.info("{} err",key);
            return null;
        }
        if(json==null){
            log.info("{} miss",key);
            return null;
        }
        log.info("{} hit",key);
        return JsonUtil.jsonToObject(json,klass);
    }
}
