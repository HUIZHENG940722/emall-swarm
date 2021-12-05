package com.ethan.mall.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.ethan.mall.admin.service.IPmsBrandService;
import com.ethan.mall.mapper.PmsBrandMapper;
import com.ethan.mall.model.PmsBrand;
import com.ethan.mall.model.PmsBrandExample;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ethan
 * @Date 7:27 PM 2021/12/5
 * @Description 商品品牌管理业务类
 */
@Service
public class PmsBrandService implements IPmsBrandService {
    @Resource
    private PmsBrandMapper brandMapper;
    @Override
    public List<PmsBrand> getList(String keyword, Integer pageNum, Integer pageSize) {
        // 1 校验
        // 2 查询
        // 2.1 开启分页
        PageHelper.startPage(pageNum, pageSize);
        // 2.2 拼装查询条件
        PmsBrandExample example = new PmsBrandExample();
        PmsBrandExample.Criteria criteria = example.createCriteria();
        if (StrUtil.isNotBlank(keyword)) {
           criteria.andNameLike("%"+keyword + "%");
        }
        example.setOrderByClause("sort desc");
        // 2.3 执行查询
        List<PmsBrand> brandList = brandMapper.selectByExample(example);
        // 3 返回结果集
        return brandList;
    }
}