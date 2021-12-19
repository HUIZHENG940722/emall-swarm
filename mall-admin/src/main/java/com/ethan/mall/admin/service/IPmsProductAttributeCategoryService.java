package com.ethan.mall.admin.service;

import com.ethan.mall.admin.domain.PmsProductAttributeCategoryItem;
import com.ethan.mall.model.PmsProductAttributeCategory;

import java.util.List;

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

    /**
     * 获取商品属性分类列表
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<PmsProductAttributeCategory> getList(Integer pageSize, Integer pageNum);

    /**
     * 修改商品类型分类
     * @param id
     * @param name
     * @return
     */
    int update(Long id, String name);

    /**
     * 获取商品类型分类及类型层级结构
     * @return
     */
    List<PmsProductAttributeCategoryItem> getListWithAttr();
}
