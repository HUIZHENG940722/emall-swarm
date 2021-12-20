package com.ethan.mall.admin.dao;

import com.ethan.mall.admin.domain.ProductCateAttrInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ethan
 * @Date 9:30 PM 2021/12/20
 * @Description 商品类型数据访问扩展
 */
public interface PmsProductAttributeDao {

    /**
     * 根据商品分类id获取商品类型参数
     * @param productCategoryId
     * @return
     */
    List<ProductCateAttrInfo> getAttrInfo(@Param(value = "productCategoryId") Long productCategoryId);
}
