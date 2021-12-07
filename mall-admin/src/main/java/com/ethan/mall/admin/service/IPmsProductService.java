package com.ethan.mall.admin.service;

import com.ethan.mall.admin.domain.PmsProductAddParam;
import com.ethan.mall.admin.domain.PmsProductQueryParam;
import com.ethan.mall.model.PmsProduct;

import java.util.List;

/**
 * @author ethan
 * @Date 9:52 PM 2021/12/4
 * @Description 商品管理业务接口
 */
public interface IPmsProductService {

    /**
     * 分页多条件查询商品列表
     * @param productQueryParam
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<PmsProduct> list(PmsProductQueryParam productQueryParam, Integer pageSize, Integer pageNum);

    /**
     * 添加商品
     * @param productAddParam
     * @return
     */
    int create(PmsProductAddParam productAddParam);
}
