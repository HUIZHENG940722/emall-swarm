package com.ethan.mall.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ethan.mall.admin.dao.PmsProductAttributeDao;
import com.ethan.mall.admin.domain.PmsProductAttributeCreateParam;
import com.ethan.mall.admin.domain.ProductCateAttrInfo;
import com.ethan.mall.admin.service.IPmsProductAttributeService;
import com.ethan.mall.mapper.PmsProductAttributeCategoryMapper;
import com.ethan.mall.mapper.PmsProductAttributeMapper;
import com.ethan.mall.model.PmsProductAttribute;
import com.ethan.mall.model.PmsProductAttributeCategory;
import com.ethan.mall.model.PmsProductAttributeExample;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author ethan
 * @Date 6:57 PM 2021/12/11
 * @Description 商品类型管理业务类
 */
@Service
public class PmsProductAttributeService implements IPmsProductAttributeService {
    @Resource
    private PmsProductAttributeMapper productAttributeMapper;
    @Resource
    private PmsProductAttributeCategoryMapper productAttributeCategoryMapper;
    @Resource
    private PmsProductAttributeDao productAttributeDao;
    @Override
    public int create(PmsProductAttributeCreateParam productAttributeCreateParam) {
        // 1 校验
        // 2 执行相应的业务逻辑
        // 2.1 插入商品类型数据
        PmsProductAttribute pmsProductAttribute = new PmsProductAttribute();
        BeanUtil.copyProperties(productAttributeCreateParam, pmsProductAttribute);
        pmsProductAttribute.setCreatedTime(new Date());
        int count = productAttributeMapper.insertSelective(pmsProductAttribute);
        // 2.2 更新商品类型分类数量
        PmsProductAttributeCategory pmsProductAttributeCategory = productAttributeCategoryMapper
                .selectByPrimaryKey(pmsProductAttribute.getProductAttributeCategoryId());
        if(pmsProductAttribute.getType()==0){
            pmsProductAttributeCategory.setAttributeCount(pmsProductAttributeCategory.getAttributeCount()+1);
        }else if(pmsProductAttribute.getType()==1){
            pmsProductAttributeCategory.setParamCount(pmsProductAttributeCategory.getParamCount()+1);
        }
        productAttributeCategoryMapper.updateByPrimaryKey(pmsProductAttributeCategory);
        // 3 返回结果集
        return count;
    }

    @Override
    public List<PmsProductAttribute> getList(Long cid, Integer type, Integer pageSize, Integer pageNum) {
        // 1 校验
        // 2 查询逻辑
        // 2.1 开启分页功能
        PageHelper.startPage(pageNum, pageSize);
        // 2.2 设置查询条件
        PmsProductAttributeExample example = new PmsProductAttributeExample();
        PmsProductAttributeExample.Criteria criteria = example.createCriteria();
        criteria.andProductAttributeCategoryIdEqualTo(cid);
        criteria.andTypeEqualTo(type);
        // 2.3 执行查询
        List<PmsProductAttribute> pmsProductAttributes = productAttributeMapper.selectByExample(example);
        // 3 返回结果集
        return pmsProductAttributes;
    }

    @Override
    public PmsProductAttribute getItem(Long id) {
        // 1 校验
        // 2 查询逻辑
        PmsProductAttribute pmsProductAttribute = productAttributeMapper.selectByPrimaryKey(id);
        // 3 返回结果集
        return pmsProductAttribute;
    }

    @Override
    public int update(Long id, PmsProductAttributeCreateParam productAttributeCreateParam) {
        // 1 校验
        // 2 更新逻辑
        // 2.1 拼装更新条件
        PmsProductAttribute productAttribute = new PmsProductAttribute();
        BeanUtil.copyProperties(productAttributeCreateParam, productAttribute);
        productAttribute.setId(id);
        // 2.2 执行更新
        int count = productAttributeMapper.updateByPrimaryKeySelective(productAttribute);
        // 3 返回结果集
        return count;
    }

    @Override
    public List<ProductCateAttrInfo> getAttrInfo(Long productCategoryId) {
        // 1 校验
        // 2 查询
        List<ProductCateAttrInfo> productCateAttrInfos = productAttributeDao.getAttrInfo(productCategoryId);
        // 3 返回结果集
        return productCateAttrInfos;
    }
}
