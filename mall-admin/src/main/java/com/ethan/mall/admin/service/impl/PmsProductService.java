package com.ethan.mall.admin.service.impl;

import com.ethan.mall.admin.domain.PmsProductQueryParam;
import com.ethan.mall.admin.service.IPmsProductService;
import com.ethan.mall.mapper.PmsProductMapper;
import com.ethan.mall.model.PmsProduct;
import com.ethan.mall.model.PmsProductExample;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ethan
 * @Date 9:53 PM 2021/12/4
 * @Description 商品管理业务类
 */
@Service
public class PmsProductService implements IPmsProductService {
    @Resource
    private PmsProductMapper productMapper;

    @Override
    public List<PmsProduct> list(PmsProductQueryParam productQueryParam, Integer pageSize, Integer pageNum) {
        // 1 校验
        // 2 查询
        // 2.1 开启分页功能
        PageHelper.startPage(pageNum, pageSize);
        // 2.2 拼装查询条件
        PmsProductExample example = new PmsProductExample();
        PmsProductExample.Criteria criteria = example.createCriteria();
        if (productQueryParam.getPublishStatus() != null) {
            criteria.andPublishStatusEqualTo(productQueryParam.getPublishStatus());
        }
        if (productQueryParam.getVerifyStatus() != null) {
            criteria.andVerifyStatusEqualTo(productQueryParam.getVerifyStatus());
        }
        if (!StringUtils.isEmpty(productQueryParam.getKeyword())) {
            criteria.andNameLike("%" + productQueryParam.getKeyword() + "%");
        }
        if (productQueryParam.getBrandId() != null) {
            criteria.andBrandIdEqualTo(productQueryParam.getBrandId());
        }
        if (productQueryParam.getProductCategoryId() != null) {
            criteria.andProductCategoryIdEqualTo(productQueryParam.getProductCategoryId());
        }
        List<PmsProduct> pmsProducts = productMapper.selectByExample(example);
        // 3 返回结果集
        return pmsProducts;
    }
}
