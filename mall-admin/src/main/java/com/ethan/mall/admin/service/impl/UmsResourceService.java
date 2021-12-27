package com.ethan.mall.admin.service.impl;

import com.ethan.mall.admin.service.IUmsResourceService;
import com.ethan.mall.mapper.UmsResourceMapper;
import com.ethan.mall.model.UmsResource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

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
}
