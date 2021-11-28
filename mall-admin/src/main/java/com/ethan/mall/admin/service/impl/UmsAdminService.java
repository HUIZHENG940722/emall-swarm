package com.ethan.mall.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.ethan.mall.admin.domain.UmsAdminRegisterParam;
import com.ethan.mall.admin.service.IUmsAdminService;
import com.ethan.mall.common.exception.Asserts;
import com.ethan.mall.mapper.UmsAdminMapper;
import com.ethan.mall.model.UmsAdmin;
import com.ethan.mall.model.UmsAdminExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ethan
 * @Date 11:51 上午 2021/11/28
 * @Description 后台用户管理业务类
 */
@Service
public class UmsAdminService implements IUmsAdminService {
    @Autowired
    private UmsAdminMapper adminMapper;
    @Override
    public UmsAdmin register(UmsAdminRegisterParam adminRegisterParam) {
        // 1 校验
        // 1.1 用户名和密码不能为空
        if (StrUtil.isBlank(adminRegisterParam.getUsername())
                || StrUtil.isBlank(adminRegisterParam.getPassword())) {
            Asserts.fail("用户名或密码不能为空");
        }
        // 1.2 校验用户名是否存在
        UmsAdminExample adminExample = new UmsAdminExample();
        adminExample.createCriteria().andUsernameEqualTo(adminRegisterParam.getUsername());
        List<UmsAdmin> umsAdmins = adminMapper.selectByExample(adminExample);
        if (CollUtil.isNotEmpty(umsAdmins)) {
            Asserts.fail("用户名已存在");
        }
        // 2 插入后台用户
        // 2.1 赋值基础数据
        UmsAdmin admin = new UmsAdmin();
        admin.setUsername(adminRegisterParam.getUsername());
        BeanUtil.copyProperties(adminRegisterParam, admin);
        // 2.2 设置创建时间
        admin.setCreateTime(new Date());
        // 2.3 设置为启用状态
        admin.setStatus(1);
        int i = adminMapper.insertSelective(admin);
        // 3 返回结果集
        return admin;
    }
}
