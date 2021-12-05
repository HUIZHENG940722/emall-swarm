package com.ethan.mall.admin.service.impl;

import com.ethan.mall.admin.dao.PmsProductCategoryDao;
import com.ethan.mall.admin.domain.PmsProductCategoryWithChildrenItem;
import com.ethan.mall.admin.service.IPmsProductCategoryService;
import com.ethan.mall.mapper.PmsProductCategoryMapper;
import com.ethan.mall.model.PmsProductCategory;
import com.ethan.mall.model.PmsProductCategoryExample;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ethan
 * @Date 9:36 PM 2021/12/4
 * @Description 商品分类业务类
 */
@Service
public class PmsProductCategoryService implements IPmsProductCategoryService {
    @Autowired
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
}
