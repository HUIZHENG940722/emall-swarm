package com.ethan.mall.admin.dao;

import com.ethan.mall.model.PmsMemberPrice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ethan
 * @Date 8:50 PM 2021/12/7
 * @Description 商品会员价格关联数据访问扩展
 */
public interface PmsMemberPriceDao {
    /**
     * 插入商品会员价格关联数据集
     * @param pmsMemberPriceList
     * @return
     */
    int insertList(@Param("list")List<PmsMemberPrice> pmsMemberPriceList);
}
