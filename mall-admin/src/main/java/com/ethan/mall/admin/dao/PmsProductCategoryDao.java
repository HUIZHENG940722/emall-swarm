package com.ethan.mall.admin.dao;

import com.ethan.mall.admin.domain.PmsProductCategoryWithChildrenItem;

import java.util.List;

/**
 * @author ethan
 * @Date 9:48 AM 2021/12/5
 * @Description 商品分类数据访问扩展
 */
public interface PmsProductCategoryDao {
    /**
     * 查询商品分类的层级结构
     * @return
     */
    List<PmsProductCategoryWithChildrenItem> listWithChildren();
}
