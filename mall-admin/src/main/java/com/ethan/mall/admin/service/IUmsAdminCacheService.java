package com.ethan.mall.admin.service;

import com.ethan.mall.model.UmsAdmin;

/**
 * @author ethan
 * @Date 8:23 AM 2022/1/6
 * @Description 后台用户缓存业务接口
 */
public interface IUmsAdminCacheService {
    /**
     * 从Redis缓存中获取后台用户信息
     * @param username
     * @return
     */
    UmsAdmin getAdmin(String username);

    /**
     * 从Redis缓存中删除用户记录
     * @param id
     */
    void delAdmin(Long id);

    /**
     * 设置Redis缓存
     * @param admin
     */
    void setAdmin(UmsAdmin admin);
}
