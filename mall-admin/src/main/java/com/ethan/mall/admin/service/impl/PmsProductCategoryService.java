package com.ethan.mall.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ethan.mall.admin.dao.PmsProductCategoryDao;
import com.ethan.mall.admin.domain.PmsProductCategoryAddParam;
import com.ethan.mall.admin.domain.PmsProductCategoryWithChildrenItem;
import com.ethan.mall.admin.service.IPmsProductCategoryService;
import com.ethan.mall.mapper.PmsProductCategoryMapper;
import com.ethan.mall.model.PmsProductCategory;
import com.ethan.mall.model.PmsProductCategoryExample;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author ethan
 * @Date 9:36 PM 2021/12/4
 * @Description 商品分类业务类
 */
@Service
public class PmsProductCategoryService implements IPmsProductCategoryService {
    @Resource
    private PmsProductCategoryMapper productCategoryMapper;
    @Resource
    private PmsProductCategoryDao productCategoryDao;
    @Override
    public List<PmsProductCategory> getList(Long parentId, Integer pageSize, Integer pageNum) {
        // 1 校验
        // 2 查询
        // 2.1 开启分页
        PageHelper.startPage(pageNum, pageSize);
        // 2.2 拼装查询条件
        PmsProductCategoryExample example = new PmsProductCategoryExample();
        example.createCriteria().andParentIdEqualTo(parentId);
        example.setOrderByClause("sort desc");
        List<PmsProductCategory> productCategoryList = productCategoryMapper.selectByExample(example);
        // 3 返回结果集
        return productCategoryList;
    }

    @Override
    public List<PmsProductCategoryWithChildrenItem> listWithChildren() {
        // 1 校验
        // 2 查询
        List<PmsProductCategoryWithChildrenItem> productCategoryWithChildrenItemList =
                productCategoryDao.listWithChildren();
        // 3 返回结果集
        return productCategoryWithChildrenItemList;
    }

    @Override
    public int create(PmsProductCategoryAddParam productCategoryAddParam) {
        // 1 校验
        // 2 添加商品分类
        PmsProductCategory productCategory = new PmsProductCategory();
        BeanUtil.copyProperties(productCategoryAddParam, productCategory);
        // 2.1 设置商品数量
        productCategory.setProductCount(0);
        // 2.2 初始化创建时间
        productCategory.setCreatedTime(new Date());
        // 2.3 设置分类层级
        setCategoryLevel(productCategory);
        // 2.4 插入商品分类
        int count = productCategoryMapper.insertSelective(productCategory);
        // 3 返回结果集
        return count;
    }

    /**
     * 设置商品分类层级
     * @param productCategory
     */
    private void setCategoryLevel(PmsProductCategory productCategory) {
        if (productCategory.getParentId() == 0) {
            productCategory.setLevel(0);
        } else {
            PmsProductCategory data = productCategoryMapper.selectByPrimaryKey(productCategory.getParentId());
            if (data != null) {
                productCategory.setLevel(data.getLevel() + 1);
            } else {
                productCategory.setLevel(0);
            }
        }
    }
}
