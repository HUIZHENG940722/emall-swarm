package com.ethan.mall.admin.service;

import com.ethan.mall.model.UmsResource;

import java.util.List;

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

    /**
     * 分页模糊查询资源列表
     * @param categoryId
     * @param nameKeyword
     * @param urlKeyword
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<UmsResource> getList(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize,
                              Integer pageNum);

    /**
     * 获取所有后台资源列表
     * @return
     */
    List<UmsResource> listAl();
}
