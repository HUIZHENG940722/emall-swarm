package com.ethan.mall.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.ethan.mall.admin.service.IUmsRoleService;
import com.ethan.mall.mapper.UmsRoleMapper;
import com.ethan.mall.model.UmsRole;
import com.ethan.mall.model.UmsRoleExample;
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
}
