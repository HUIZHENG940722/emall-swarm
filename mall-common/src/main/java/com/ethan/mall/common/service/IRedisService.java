package com.ethan.mall.common.service;

import com.ethan.mall.model.UmsAdmin;

/**
 * @author ethan
 * @Date 8:13 AM 2022/1/6
 * @Description redis操作Service
 */
public interface IRedisService {
    /**
     * 获取属性值
     * @param key
     * @return
     */
    Object get(String key);

    /**
     * 根据关键字删除属性
     * @param key
     */
    void del(String key);

    /**
     * 保存属性
     * @param key
     * @param value
     * @param redis_expire
     */
    void set(String key, Object value, Long redis_expire);
}
