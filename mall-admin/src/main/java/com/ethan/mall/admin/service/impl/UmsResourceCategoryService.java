package com.ethan.mall.admin.service.impl;

import com.ethan.mall.admin.service.IUmsResourceCategoryService;
import com.ethan.mall.mapper.UmsResourceCategoryMapper;
import com.ethan.mall.model.UmsResourceCategory;
import com.ethan.mall.model.UmsResourceCategoryExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author ethan
 * @Date 8:27 AM 2021/12/27
 * @Description 后台资源分类管理业务类
 */
@Service
public class UmsResourceCategoryService implements IUmsResourceCategoryService {
    @Resource
    private UmsResourceCategoryMapper resourceCategoryMapper;
    @Override
    public int create(UmsResourceCategory resourceCategory) {
        // 1 校验
        // 2 创建
        // 2.1 初始化数据
        resourceCategory.setCreatedTime(new Date());
        // 2.2 插入数据
        int count = resourceCategoryMapper.insertSelective(resourceCategory);
        // 3 返回结果集
        return count;
    }

    @Override
    public List<UmsResourceCategory> getListAll() {
        // 1 校验
        // 2 查询
        List<UmsResourceCategory> resourceCategoryList = resourceCategoryMapper
                .selectByExample(new UmsResourceCategoryExample());
        // 3 返回结果集
        return resourceCategoryList;
    }
}
