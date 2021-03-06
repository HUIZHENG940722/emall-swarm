package com.ethan.mall.admin.service;

import com.ethan.mall.model.UmsResourceCategory;

import java.util.List;

/**
 * @author ethan
 * @Date 8:26 AM 2021/12/27
 * @Description 后台资源分类管理业务接口
 */
public interface IUmsResourceCategoryService {
    /**
     * 创建资源分类
     * @param resourceCategory
     * @return
     */
    int create(UmsResourceCategory resourceCategory);

    /**
     * 查询所有的资源分类
     * @return
     */
    List<UmsResourceCategory> getListAll();

    /**
     * 更新资源分类
     * @param id
     * @param resource
     * @return
     */
    int update(Long id, UmsResourceCategory resource);
}
