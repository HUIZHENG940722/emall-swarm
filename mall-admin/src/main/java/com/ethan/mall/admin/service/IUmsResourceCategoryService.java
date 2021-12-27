package com.ethan.mall.admin.service;

import com.ethan.mall.model.UmsResourceCategory;

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
}
