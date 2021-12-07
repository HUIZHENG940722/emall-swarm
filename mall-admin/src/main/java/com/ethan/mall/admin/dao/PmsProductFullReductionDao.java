package com.ethan.mall.admin.dao;

import com.ethan.mall.model.PmsProductFullReduction;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ethan
 * @Date 8:39 PM 2021/12/7
 * @Description 商品满减价格关联数据访问扩展
 */
public interface PmsProductFullReductionDao {
    /**
     * 插入商品满减价格关联数据集
     * @param productFullReductionList
     * @return
     */
    int insertList(@Param("list")List<PmsProductFullReduction> productFullReductionList);
}
