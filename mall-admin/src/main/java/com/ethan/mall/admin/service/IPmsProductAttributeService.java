package com.ethan.mall.admin.service;

import com.ethan.mall.admin.domain.PmsProductAttributeCreateParam;

/**
 * @author ethan
 * @Date 6:56 PM 2021/12/11
 * @Description 商品类型管理业务接口
 */
public interface IPmsProductAttributeService {
    /**
     * 添加商品类型信息
     * @param productAttributeCreateParam
     * @return
     */
    int create(PmsProductAttributeCreateParam productAttributeCreateParam);
}
