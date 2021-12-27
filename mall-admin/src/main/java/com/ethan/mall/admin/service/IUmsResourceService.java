package com.ethan.mall.admin.service;

import com.ethan.mall.model.UmsResource;

/**
 * @author ethan
 * @Date 9:28 PM 2021/12/27
 * @Description 后台资源管理业务接口
 */
public interface IUmsResourceService {
    /**
     * 创建后台资源
     * @param umsResource
     * @return
     */
    int create(UmsResource umsResource);
}
