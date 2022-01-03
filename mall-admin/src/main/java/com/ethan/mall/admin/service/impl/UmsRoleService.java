package com.ethan.mall.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.ethan.mall.admin.dao.UmsRoleDao;
import com.ethan.mall.admin.service.IUmsRoleService;
import com.ethan.mall.mapper.UmsRoleMapper;
import com.ethan.mall.mapper.UmsRoleMenuRelationMapper;
import com.ethan.mall.mapper.UmsRoleResourceRelationMapper;
import com.ethan.mall.model.*;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author ethan
 * @Date 3:51 PM 2021/12/26
 * @Description 后台用户角色管理业务类
 */
@Service
public class UmsRoleService implements IUmsRoleService {
    @Resource
    private UmsRoleMapper roleMapper;
    @Resource
    private UmsRoleMenuRelationMapper roleMenuRelationMapper;
    @Resource
    private UmsRoleDao roleDao;
    @Resource
    private UmsRoleResourceRelationMapper roleResourceRelationMapper;
    @Override
    public List<UmsRole> getList(String keyword, Integer pageNum, Integer pageSize) {
        // 1 校验
        // 2 查询
        // 2.1 开启分页功能
        PageHelper.startPage(pageNum, pageSize);
        // 2.2 拼装查询条件
        UmsRoleExample roleExample = new UmsRoleExample();
        if (StrUtil.isNotBlank(keyword)) {
            UmsRoleExample.Criteria criteria = roleExample.createCriteria();
            criteria.andNameLike("%" + keyword + "%");
        }
        // 2.3 执行查询
        List<UmsRole> umsRoleList = roleMapper.selectByExample(roleExample);
        // 3 返回结果集
        return umsRoleList;
    }

    @Override
    public int create(UmsRole role) {
        // 1 校验
        // 2 插入
        // 2.1 初始化数据
        role.setCreatedTime(new Date());
        int count = roleMapper.insertSelective(role);
        // 3 返回结果集
        return count;
    }

    @Override
    public int update(Long id, UmsRole role) {
        // 1 校验
        // 2 更新信息
        role.setId(id);
        role.setUpdatedTime(new Date());
        int count = roleMapper.updateByPrimaryKeySelective(role);
        // 3 返回结果集
        return count;
    }

    @Override
    public int allocMenu(Long roleId, List<Long> menuIds) {
        // 1 校验
        // 2 分配菜单逻辑
        // 2.1 先删除原来的角色菜单关联信息
        UmsRoleMenuRelationExample example = new UmsRoleMenuRelationExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        roleMenuRelationMapper.deleteByExample(example);
        // 2.2 再批量插入
        for (Long menuId : menuIds) {
            UmsRoleMenuRelation roleMenuRelation = new UmsRoleMenuRelation();
            roleMenuRelation.setRoleId(roleId);
            roleMenuRelation.setMenuId(menuId);
            roleMenuRelation.setCreatedTime(new Date());
            roleMenuRelationMapper.insertSelective(roleMenuRelation);
        }
        // 3 返回结果集
        return menuIds.size();
    }

    @Override
    public List<UmsMenu> menuList(Long roleId) {
        // 1 校验
        // 2 查询
        List<UmsMenu> menuList = roleDao.getMenuListByRoleId(roleId);
        // 3 返回结果
        return menuList;
    }

    @Override
    public List<UmsResource> listResources(Long roleId) {
        // 1 校验
        // 2 查询
        List<UmsResource> resourceList = roleDao.getResourceListByRoleId(roleId);
        // 3 返回结果集
        return resourceList;
    }

    @Override
    public int allocResource(Long roleId, List<Long> resourceIds) {
        // 1 校验
        // 2 分配逻辑
        // 2.1 先删除原有关系
        UmsRoleResourceRelationExample example = new UmsRoleResourceRelationExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        roleResourceRelationMapper.deleteByExample(example);
        // 2.2 插入角色资源关联数据集
        for (Long resourceId : resourceIds) {
            UmsRoleResourceRelation relation = new UmsRoleResourceRelation();
            relation.setRoleId(roleId);
            relation.setResourceId(resourceId);
            relation.setCreatedTime(new Date());
            roleResourceRelationMapper.insertSelective(relation);
        }
        // 3 返回结果
        return resourceIds.size();
    }

    @Override
    public List<UmsRole> listAll() {
        // 1 校验
        // 2 查询
        List<UmsRole> roleList = roleMapper.selectByExample(new UmsRoleExample());
        // 3 返回结果集
        return roleList;
    }
}
