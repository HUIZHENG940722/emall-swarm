package com.ethan.mall.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ethan.mall.admin.domain.PmsProductAttributeCreateParam;
import com.ethan.mall.admin.service.IPmsProductAttributeService;
import com.ethan.mall.mapper.PmsProductAttributeCategoryMapper;
import com.ethan.mall.mapper.PmsProductAttributeMapper;
import com.ethan.mall.model.PmsProductAttribute;
import com.ethan.mall.model.PmsProductAttributeCategory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

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
}
