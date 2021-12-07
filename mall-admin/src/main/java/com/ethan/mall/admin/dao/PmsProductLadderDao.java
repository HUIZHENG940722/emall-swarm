package com.ethan.mall.admin.dao;

import com.ethan.mall.model.PmsProductLadder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ethan
 * @Date 8:17 PM 2021/12/7
 * @Description 商品阶梯价格关联数据访问扩展
 */
public interface PmsProductLadderDao {
    /**
     * 插入商品阶梯价格关联数据集
     * @param productLadderList
     * @return
     */
    int insertList(@Param("list") List<PmsProductLadder> productLadderList);
}
