package com.ethan.mall.admin.service.impl;

import com.ethan.mall.admin.dao.PmsProductAttributeCategoryDao;
import com.ethan.mall.admin.domain.PmsProductAttributeCategoryItem;
import com.ethan.mall.admin.service.IPmsProductAttributeCategoryService;
import com.ethan.mall.mapper.PmsProductAttributeCategoryMapper;
import com.ethan.mall.model.PmsProductAttributeCategory;
import com.ethan.mall.model.PmsProductAttributeCategoryExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author ethan
 * @Date 2:02 PM 2021/12/11
 * @Description 商品类型管理业务类
 */
@Service
public class PmsProductAttributeCategoryService implements IPmsProductAttributeCategoryService {
    @Resource
    private PmsProductAttributeCategoryMapper productAttributeCategoryMapper;
    @Resource
    private PmsProductAttributeCategoryDao productAttributeCategoryDao;
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

    @Override
    public List<PmsProductAttributeCategory> getList(Integer pageSize, Integer pageNum) {
        // 1 校验
        // 2 查询
        PmsProductAttributeCategoryExample example = new PmsProductAttributeCategoryExample();
        List<PmsProductAttributeCategory> productAttributeCategoryList =
                productAttributeCategoryMapper.selectByExample(example);
        // 3 返回结果集
        return productAttributeCategoryList;
    }

    @Override
    public int update(Long id, String name) {
        // 1 校验
        // 2 更新
        PmsProductAttributeCategory productAttributeCategory = new PmsProductAttributeCategory();
        productAttributeCategory.setName(name);
        productAttributeCategory.setId(id);
        productAttributeCategory.setUpdatedTime(new Date());
        int count = productAttributeCategoryMapper.updateByPrimaryKeySelective(productAttributeCategory);
        // 3 返回结果集
        return count;
    }

    @Override
    public List<PmsProductAttributeCategoryItem> getListWithAttr() {
        // 1 校验
        // 2 执行相应的逻辑
        List<PmsProductAttributeCategoryItem> productAttributeCategoryItems = productAttributeCategoryDao
                .getListWithAttr();
        // 3 返回结果集
        return productAttributeCategoryItems;
    }
}
