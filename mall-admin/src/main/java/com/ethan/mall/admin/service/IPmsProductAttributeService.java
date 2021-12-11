package com.ethan.mall.admin.service;

import com.ethan.mall.admin.domain.PmsProductAttributeCreateParam;
import com.ethan.mall.model.PmsProductAttribute;

import java.util.List;

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

    /**
     * 根据类型分类查询类型列表
     * @param cid
     * @param type
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<PmsProductAttribute> getList(Long cid, Integer type, Integer pageSize, Integer pageNum);

    /**
     * 根据商品类型id获取商品
     * @param id
     * @return
     */
    PmsProductAttribute getItem(Long id);
}
