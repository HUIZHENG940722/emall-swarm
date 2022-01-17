package com.ethan.mall.common.service.impl;

import com.ethan.mall.common.service.IRedisService;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author ethan
 * @Date 8:13 AM 2022/1/6
 * @Description redis操作类
 */
public class RedisService implements IRedisService {
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void del(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void set(String key, Object value, Long redisExpire) {
        redisTemplate.opsForValue().set(key, value, redisExpire, TimeUnit.SECONDS);
    }

    @Override
    public void hSetAll(String key, Map<String, List<String>> map) {
       redisTemplate.opsForHash().putAll(key, map);
    }
}
