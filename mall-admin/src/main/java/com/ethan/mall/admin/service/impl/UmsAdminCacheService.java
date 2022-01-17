package com.ethan.mall.admin.service.impl;

import com.ethan.mall.admin.service.IUmsAdminCacheService;
import com.ethan.mall.admin.service.IUmsAdminService;
import com.ethan.mall.common.service.IRedisService;
import com.ethan.mall.model.UmsAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ethan
 * @Date 8:24 AM 2022/1/6
 * @Description 后台用户缓存类
 */
@Service
public class UmsAdminCacheService implements IUmsAdminCacheService {
    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.key.admin}")
    private String REDIS_KEY_ADMIN;
    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;
    @Resource
    private IRedisService redisService;
    @Resource
    private IUmsAdminService adminService;
    @Override
    public UmsAdmin getAdmin(Long adminId) {
        // 1 校验
        // 2 执行逻辑
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + adminId;
        UmsAdmin admin = (UmsAdmin) redisService.get(key);
        // 3 返回结果集
        return admin;
    }

    @Override
    public void delAdmin(Long adminId) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + adminId;
        redisService.del(key);
    }

    @Override
    public void setAdmin(UmsAdmin admin) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + admin.getId();
        redisService.set(key, admin, REDIS_EXPIRE);
    }
}
