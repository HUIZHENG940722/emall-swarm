package com.ethan.mall.admin.service;

/**
 * @author ethan
 * @Date 2:01 PM 2021/12/11
 * @Description 商品类型管理业务接口
 */
public interface IPmsProductAttributeCategoryService {
    /**
     * 创建商品属性分类
     * @param name
     * @return
     */
    int create(String name);
}
