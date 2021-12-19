package com.ethan.mall.admin.dao;

import com.ethan.mall.admin.domain.PmsProductAttributeCategoryItem;

import java.util.List;

/**
 * @author ethan
 * @Date 9:02 AM 2021/12/19
 * @Description 商品类型分类数据访问扩展
 */
public interface PmsProductAttributeCategoryDao {

    /**
     * 获取商品类型分类及类型层级结构
     * @return
     */
    List<PmsProductAttributeCategoryItem> getListWithAttr();
}
