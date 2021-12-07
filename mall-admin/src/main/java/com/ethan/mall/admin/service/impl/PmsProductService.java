package com.ethan.mall.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.ethan.mall.admin.dao.*;
import com.ethan.mall.admin.domain.PmsProductAddParam;
import com.ethan.mall.admin.domain.PmsProductQueryParam;
import com.ethan.mall.admin.service.IPmsProductService;
import com.ethan.mall.mapper.PmsProductMapper;
import com.ethan.mall.model.*;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ethan
 * @Date 9:53 PM 2021/12/4
 * @Description 商品管理业务类
 */
@Service
public class PmsProductService implements IPmsProductService {
    @Resource
    private PmsProductMapper productMapper;
    @Resource
    private PmsProductLadderDao productLadderDao;
    @Resource
    private PmsProductFullReductionDao productFullReductionDao;
    @Resource
    private PmsMemberPriceDao memberPriceDao;
    @Resource
    private PmsSkuStockDao skuStockDao;
    @Resource
    private PmsProductAttributeValueDao productAttributeValueDao;
    @Resource
    private CmsSubjectProductRelationDao subjectProductRelationDao;
    @Resource
    private CmsPreferenceAreaProductRelationDao preferenceAreaProductRelationDao;

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

    @Override
    public int create(PmsProductAddParam productAddParam) {
        // 1 校验
        // 2 执行创建商品逻辑
        // 2.1 插入商品信息
        PmsProduct product = productAddParam;
        productMapper.insertSelective(product);
        long productId = product.getId();
        // 2.2 插入商品阶梯价格关联数据
        List<PmsProductLadder> productLadderList = productAddParam.getProductLadderList();
        productLadderList.forEach(productLadder -> {
            productLadder.setCreatedTime(new Date());
            productLadder.setProductId(productId);
        });
        productLadderDao.insertList(productLadderList);
        // 2.3 插入商品满减价格关联数据
        List<PmsProductFullReduction> productFullReductionList = productAddParam.getProductFullReductionList();
        productFullReductionList.forEach(item -> {
            item.setCreatedTime(new Date());
            item.setProductId(productId);
        });
        productFullReductionDao.insertList(productFullReductionList);
        // 2.4 插入商品会员价格关联数据
        List<PmsMemberPrice> memberPriceList = productAddParam.getMemberPriceList();
        memberPriceList.forEach(item -> {
            item.setCreatedTime(new Date());
            item.setProductId(productId);
        });
        memberPriceDao.insertList(memberPriceList);
        // 2.5 sku库存信息
        List<PmsSkuStock> skuStockList = productAddParam.getSkuStockList();
        AtomicInteger i = new AtomicInteger();
        skuStockList.forEach(item -> {
            if (StrUtil.isBlank(item.getSkuCode())) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                StringBuilder sb = new StringBuilder();
                //日期
                sb.append(sdf.format(new Date()));
                //四位商品id
                sb.append(String.format("%04d", productId));
                //三位索引id
                sb.append(String.format("%03d", i.get() +1));
                item.setSkuCode(sb.toString());
                i.set(i.get() + 1);
            }
            item.setCreatedTime(new Date());
            item.setProductId(productId);
        });
        skuStockDao.insertList(skuStockList);
        // 2.6 插入商品自定义参数、规格
        List<PmsProductAttributeValue> productAttributeValueList = productAddParam.getProductAttributeValueList();
        productAttributeValueList.forEach(item -> {
            item.setCreatedTime(new Date());
            item.setProductId(productId);
        });
        productAttributeValueDao.insertList(productAttributeValueList);
        // 2.7 插入专题商品关联数据
        List<CmsSubjectProductRelation> subjectProductRelationList = productAddParam.getSubjectProductRelationList();
        subjectProductRelationList.forEach(item -> {
            item.setProductId(productId);
            item.setCreatedTime(new Date());
        });
        subjectProductRelationDao.insertList(subjectProductRelationList);
        // 2.8 插入优选商品关联数据
        List<CmsPreferenceAreaProductRelation> preferenceAreaProductRelationList = productAddParam.getPreferenceAreaProductRelationList();
        preferenceAreaProductRelationList.forEach(item -> {
            item.setCreatedTime(new Date());
            item.setProductId(productId);
        });
        preferenceAreaProductRelationDao.insertList(preferenceAreaProductRelationList);
        // 3 返回结果集
        return 1;
    }
}
