package com.ethan.mall.admin.service;

import com.ethan.mall.model.PmsProductCategory;

import java.util.List;

/**
 * @author ethan
 * @Date 9:36 PM 2021/12/4
 * @Description 商品分类业务接口
 */
public interface IPmsProductCategoryService {
    /**
     * 分页查询商品分类列表
     * @param parentId
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<PmsProductCategory> getList(Long parentId, Integer pageSize, Integer pageNum);
}
