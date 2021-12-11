package com.ethan.mall.admin.service.impl;

import com.ethan.mall.admin.service.IPmsProductAttributeCategoryService;
import com.ethan.mall.mapper.PmsProductAttributeCategoryMapper;
import com.ethan.mall.model.PmsProductAttributeCategory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author ethan
 * @Date 2:02 PM 2021/12/11
 * @Description 商品类型管理业务类
 */
@Service
public class PmsProductAttributeCategoryService implements IPmsProductAttributeCategoryService {
    @Resource
    private PmsProductAttributeCategoryMapper productAttributeCategoryMapper;
    @Override
    public int create(String name) {
        // 1 校验
        // 2 执行创建属性分类
        PmsProductAttributeCategory productAttributeCategory = new PmsProductAttributeCategory();
        productAttributeCategory.setName(name);
        productAttributeCategory.setCreatedTime(new Date());
        int count = productAttributeCategoryMapper.insertSelective(productAttributeCategory);
        // 3 返回结果集
        return count;
    }
}
