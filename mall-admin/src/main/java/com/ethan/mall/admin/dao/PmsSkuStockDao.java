package com.ethan.mall.admin.dao;

import com.ethan.mall.model.PmsSkuStock;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ethan
 * @Date 9:39 PM 2021/12/7
 * @Description 商品SKU库存数据访问扩展
 */
public interface PmsSkuStockDao {
    /**
     * 插入商品SKU库存信息数据集
     * @param skuStockList
     * @return
     */
    int insertList(@Param("list")List<PmsSkuStock> skuStockList);
}
