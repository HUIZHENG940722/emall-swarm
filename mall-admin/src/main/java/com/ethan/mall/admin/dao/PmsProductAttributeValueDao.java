package com.ethan.mall.admin.dao;

import com.ethan.mall.model.PmsProductAttributeValue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ethan
 * @Date 9:56 PM 2021/12/7
 * @Description 商品参数、规格数据访问扩展
 */
public interface PmsProductAttributeValueDao {
    /**
     * 插入商品参数、规则值数据集
     * @param productAttributeValueList
     * @return
     */
    int insertList(@Param("list") List<PmsProductAttributeValue> productAttributeValueList);
}
