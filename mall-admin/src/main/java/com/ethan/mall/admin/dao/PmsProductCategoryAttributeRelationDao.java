package com.ethan.mall.admin.dao;

import com.ethan.mall.model.PmsProductCategoryAttributeRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ethan
 * @Date 10:39 PM 2021/12/20
 * @Description 商品分类类型关联数据访问扩展
 */
public interface PmsProductCategoryAttributeRelationDao {
    /**
     * 批量创建商品分类类型关联信息
     * @param relationList
     * @return
     */
    int insertList(@Param(value = "list") List<PmsProductCategoryAttributeRelation> relationList);
}
