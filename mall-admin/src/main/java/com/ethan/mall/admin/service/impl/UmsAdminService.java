package com.ethan.mall.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.ethan.mall.admin.dao.UmsAdminRoleRelationDao;
import com.ethan.mall.admin.domain.UmsAdminRegisterParam;
import com.ethan.mall.admin.service.IUmsAdminService;
import com.ethan.mall.common.domain.LoginUser;
import com.ethan.mall.common.exception.Asserts;
import com.ethan.mall.mapper.UmsAdminMapper;
import com.ethan.mall.model.UmsAdmin;
import com.ethan.mall.model.UmsAdminExample;
import com.ethan.mall.model.UmsRole;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ethan
 * @Date 11:51 上午 2021/11/28
 * @Description 后台用户管理业务类
 */
@Service
public class UmsAdminService implements IUmsAdminService {
    @Resource
    private UmsAdminMapper adminMapper;

    @Resource
    private UmsAdminRoleRelationDao adminRoleRelationDao;
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
        // 1.3 邮箱校验
        String email = adminRegisterParam.getEmail();
        if (StrUtil.isNotBlank(email) && !email.matches(
                "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$")) {
            Asserts.fail("邮箱非法");
        }
        // 2 插入后台用户
        // 2.1 赋值基础数据
        UmsAdmin admin = new UmsAdmin();
        admin.setUsername(adminRegisterParam.getUsername());
        BeanUtil.copyProperties(adminRegisterParam, admin);
        // 2.2 设置创建时间
        admin.setCreatedTime(new Date());
        // 2.3 插入用户信息
        int i = adminMapper.insertSelective(admin);
        // 3 返回结果集
        return admin;
    }

    @Override
    public List<UmsAdmin> getList(String keyword, Integer pageSize, Integer pageNum) {
        // 1 校验
        // 2 查询
        // 1.1 开启分页
        PageHelper.startPage(pageNum, pageSize);
        // 1.2 拼装查询条件
        UmsAdminExample adminExample = new UmsAdminExample();
        if (StrUtil.isNotBlank(keyword)) {
            UmsAdminExample.Criteria criteria = adminExample.createCriteria();
            criteria.andUsernameLike("%" + keyword + '%');
            adminExample.or(adminExample.createCriteria().andNickNameLike("%" + keyword + "%"));
        }
        // 1.3 执行查询
        List<UmsAdmin> adminList = adminMapper.selectByExample(adminExample);
        // 3 返回结果集
        return adminList;
    }

    @Override
    public int update(Long id, UmsAdminRegisterParam adminRegisterParam) {
        // 1 校验
        // 2 更新
        // 2.1 初始化初始数据
        UmsAdmin admin = new UmsAdmin();
        BeanUtil.copyProperties(adminRegisterParam, admin);
        admin.setId(id);
        // 2.2 初始化更新时间
        admin.setUpdatedTime(new Date());
        // 2.3 更新数据
        int count = adminMapper.updateByPrimaryKeySelective(admin);
        // 3 返回结果集
        return count;
    }

    @Override
    public LoginUser loadUserByUsername(String username) {
        // 1 校验
        // 2 查询
        // 2.1 查询后台用户
        UmsAdmin admin = getByUsername(username);
        if (admin != null) {
            List<UmsRole> roleList = getRoleList(admin.getId());
            LoginUser loginUser = new LoginUser();
            BeanUtil.copyProperties(username, loginUser);
            if (CollUtil.isNotEmpty(roleList)) {
                List<String> roleStrList = roleList.stream().map(item -> item.getId() + "_"
                        + item.getName()).collect(Collectors.toList());
                loginUser.setRoles(roleStrList);
            }
            return loginUser;
        }
        // 3 返回结果集
        return null;
    }


    @Override
    public List<UmsRole> getRoleList(Long adminId) {
        // 1 校验
        // 2 查询
        List<UmsRole> roleList = adminRoleRelationDao.getRoleList(adminId);
        // 3 返回结果集
        return roleList;
    }

    @Override
    public UmsAdmin getByUsername(String username) {
        // 1 校验
        // 2 查询
        // 2.1 拼装查询条件
        UmsAdminExample adminExample = new UmsAdminExample();
        adminExample.createCriteria().andUsernameEqualTo(username);
        // 2.2 执行查询
        List<UmsAdmin> umsAdmins = adminMapper.selectByExample(adminExample);
        // 3 返回结果集
        if (CollUtil.isNotEmpty(umsAdmins)) {
            return umsAdmins.get(0);
        }
        return null;
    }
}
