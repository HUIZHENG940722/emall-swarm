package com.ethan.mall.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.ethan.mall.admin.service.IUmsResourceService;
import com.ethan.mall.common.constant.AuthConstant;
import com.ethan.mall.common.service.IRedisService;
import com.ethan.mall.mapper.UmsResourceMapper;
import com.ethan.mall.mapper.UmsRoleMapper;
import com.ethan.mall.mapper.UmsRoleResourceRelationMapper;
import com.ethan.mall.model.*;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ethan
 * @Date 9:28 PM 2021/12/27
 * @Description 后台资源管理业务类
 */
@Service
public class UmsResourceService implements IUmsResourceService {

    @Resource
    private UmsResourceMapper resourceMapper;

    @Resource
    private UmsRoleMapper roleMapper;

    @Resource
    private UmsRoleResourceRelationMapper roleResourceRelationMapper;

    @Value("${spring.application.name}")
    private String applicationName;

    @Resource
    private IRedisService redisService;

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

    @Override
    public List<UmsResource> listAl() {
        // 1 校验
        // 2 查询
        List<UmsResource> resourceList = resourceMapper.selectByExample(new UmsResourceExample());
        // 3 返回结果集
        return resourceList;
    }

    @Override
    public Map<String, List<String>> initResourceRolesMap() {
        // 1 校验
        // 2 初始化资角色关联
        Map<String, List<String>> resourceRolesMap = new TreeMap<>();
        // 2.1 获取所有的资源列表
        List<UmsResource> resourceList = resourceMapper.selectByExample(new UmsResourceExample());
        // 2.2 获取所有的角色列表
        List<UmsRole> roleList = roleMapper.selectByExample(new UmsRoleExample());
        // 2.3 获取资源角色关联列表
        List<UmsRoleResourceRelation> relationList = roleResourceRelationMapper
                .selectByExample(new UmsRoleResourceRelationExample());
        for (UmsResource umsResource : resourceList) {
            // 2.4 获取该资源对应的角色id列表
            Set<Long> roleIds = relationList.stream().filter(item -> item.getResourceId().equals(umsResource.getId()))
                    .map(UmsRoleResourceRelation::getRoleId).collect(Collectors.toSet());
            List<String> roleNames = roleList.stream().filter(item -> roleIds.contains(item.getId())).map(item
                    -> item.getId() + "_" + item.getName()).collect(Collectors.toList());
            resourceRolesMap.put("/" + applicationName + umsResource.getUrl(), roleNames);
        }
        redisService.del(AuthConstant.RESOURCE_ROLES_MAP_KEY);
        redisService.hSetAll(AuthConstant.RESOURCE_ROLES_MAP_KEY, resourceRolesMap);
        return resourceRolesMap;
    }


}
