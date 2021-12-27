package com.ethan.mall.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.ethan.mall.admin.service.IUmsResourceService;
import com.ethan.mall.mapper.UmsResourceMapper;
import com.ethan.mall.model.UmsResource;
import com.ethan.mall.model.UmsResourceExample;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author ethan
 * @Date 9:28 PM 2021/12/27
 * @Description 后台资源管理业务类
 */
@Service
public class UmsResourceService implements IUmsResourceService {

    @Resource
    private UmsResourceMapper resourceMapper;

    @Override
    public int create(UmsResource umsResource) {
        // 1 校验
        // 2 创建资源
        // 2.1 插入赋值
        umsResource.setCreatedTime(new Date());
        // 2.2 执行插入逻辑
        int count = resourceMapper.insertSelective(umsResource);
        // 3 返回结果集
        return count;
    }

    @Override
    public List<UmsResource> getList(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum) {
        // 1 校验
        // 2 分页查询
        // 2.1 开启分页功能
        PageHelper.startPage(pageNum, pageSize);
        // 2.2 拼装查询条件
        UmsResourceExample example = new UmsResourceExample();
        UmsResourceExample.Criteria criteria = example.createCriteria();
        if (categoryId != null) {
            criteria.andCategoryIdEqualTo(categoryId);
        }
        if (StrUtil.isNotBlank(nameKeyword)) {
            criteria.andNameLike("%" + nameKeyword + "%");
        }
        if (StrUtil.isNotBlank(urlKeyword)) {
            criteria.andUrlLike("%" + urlKeyword + "%");
        }
        // 2.3 执行查询
        List<UmsResource> resourceList = resourceMapper.selectByExample(example);
        // 3 返回结果集
        return resourceList;
    }


}
