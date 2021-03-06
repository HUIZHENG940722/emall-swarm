package com.ethan.mall.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.BCrypt;
import cn.hutool.json.JSONUtil;
import com.ethan.mall.admin.dao.UmsAdminDao;
import com.ethan.mall.admin.dao.UmsAdminRoleRelationDao;
import com.ethan.mall.admin.domain.UmsAdminRegisterParam;
import com.ethan.mall.admin.service.IUmsAdminCacheService;
import com.ethan.mall.admin.service.IUmsAdminService;
import com.ethan.mall.admin.service.feign.IAuthService;
import com.ethan.mall.common.api.CommonData;
import com.ethan.mall.common.api.ResultCode;
import com.ethan.mall.common.constant.AuthConstant;
import com.ethan.mall.common.domain.LoginUser;
import com.ethan.mall.common.exception.Asserts;
import com.ethan.mall.mapper.UmsAdminLoginLogMapper;
import com.ethan.mall.mapper.UmsAdminMapper;
import com.ethan.mall.mapper.UmsAdminRoleRelationMapper;
import com.ethan.mall.model.*;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
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

    @Resource
    private UmsAdminRoleRelationMapper adminRoleRelationMapper;
    @Resource
    private UmsAdminDao adminDao;
    @Resource
    private IUmsAdminCacheService adminCacheService;
    @Resource
    private IAuthService authService;
    @Resource
    private HttpServletRequest request;
    @Resource
    private UmsAdminLoginLogMapper loginLogMapper;
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
        BeanUtil.copyProperties(adminRegisterParam, admin);
        // 2.2 设置创建时间
        admin.setCreatedTime(new Date());
        // 2.3 对用户密码进行加密
        admin.setPassword(BCrypt.hashpw(admin.getPassword()));
        // 2.4 插入用户信息
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
        // 2.4 从缓存中删除用户记录
        adminCacheService.delAdmin(id);
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
            BeanUtil.copyProperties(admin, loginUser);
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
    public int updateRole(Long adminId, List<Long> roleIds) {
        // 1 校验
        // 2 分配角色逻辑
        // 2.1 删除原来的用户角色关联信息
        UmsAdminRoleRelationExample adminRoleRelationExample = new UmsAdminRoleRelationExample();
        adminRoleRelationExample.createCriteria().andAdminIdEqualTo(adminId);
        adminRoleRelationMapper.deleteByExample(adminRoleRelationExample);
        // 2.2 插入新的用户角色关联数据
        if (CollUtil.isNotEmpty(roleIds)) {
            List<UmsAdminRoleRelation> list = new ArrayList<>();
            for (Long roleId : roleIds) {
                UmsAdminRoleRelation roleRelation = new UmsAdminRoleRelation();
                roleRelation.setAdminId(adminId);
                roleRelation.setRoleId(roleId);
                roleRelation.setCreatedTime(new Date());
                list.add(roleRelation);
            }
            adminRoleRelationDao.insertList(list);
        }
        // 2.3 从缓存中删除用户记录
        adminCacheService.delAdmin(adminId);
        // 3 返回结果
        return roleIds.size();
    }

    @Override
    public Map<String, Object> getAdminInfo() {
        // 1 校验
        // 2 获取逻辑
        // 2.1 获取用户名信息
        String userStr = request.getHeader(AuthConstant.USER_TOKEN_HEADER);
        // 2.2 验证用户名信息是否有效
        if (StrUtil.isEmpty(userStr)) {
            Asserts.fail(ResultCode.UNAUTHORIZED);
        }
        // 2.3 填充相应的登录信息
        Map<String, Object> data = new HashMap<>(10);
        LoginUser loginUser = JSONUtil.toBean(userStr, LoginUser.class);
        UmsAdmin admin = adminCacheService.getAdmin(loginUser.getId());
        if (admin != null) {
            data.put("username", admin.getUsername());
        } else {
            admin = adminMapper.selectByPrimaryKey(loginUser.getId());
            adminCacheService.setAdmin(admin);
            data.put("username", admin.getUsername());
        }
        // 2.4 获取该用户菜单项
        data.put("menus", getMenuList(admin.getId()));
        data.put("icon", admin.getIcon());
        // 2.5 获取该用户角色
        List<UmsRole> roleList = getRoleList(admin.getId());
        if(CollUtil.isNotEmpty(roleList)){
            List<String> roles = roleList.stream().map(UmsRole::getName).collect(Collectors.toList());
            data.put("roles",roles);
        }
        // 3 返回结果
        return data;
    }

    @Override
    public List<UmsMenu> getMenuList(Long adminId) {
        // 1 校验
        // 2 获取逻辑
        List<UmsMenu> menuList = adminDao.getMenuList(adminId);
        // 3 返回结果集
        return menuList;
    }

    @Override
    public UmsAdmin getByAdminId(Long id) {
        // 1 校验
        // 2 查询逻辑
        UmsAdmin admin = adminMapper.selectByPrimaryKey(id);
        // 3 返回结果集
        return admin;
    }

    @Override
    public CommonData login(String username, String password) {
        // 1 校验
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            Asserts.fail("用户或密码不能为空");
        }
        // 2 执行逻辑
        Map<String, String> params = new HashMap<>(10);
        params.put("client_id", AuthConstant.ADMIN_CLIENT_ID);
        params.put("client_secret", "123456");
        params.put("grant_type", "password");
        params.put("username", username);
        params.put("password", password);
        CommonData accessToken = authService.getAccessToken(params);
        // 3 返回结果集
        if (ResultCode.SUCCESS.getCode().equals(accessToken.getCode())) {
            updateLoginTimeByUsername(username);
            insertLoginLog(username);
        }
        return accessToken;
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
            UmsAdmin umsAdmin = umsAdmins.get(0);
            // 设置redis缓存
            adminCacheService.setAdmin(umsAdmin);
            return umsAdmin;
        }
        return null;
    }

    /**
     * 根据用户名更新登录时间
     * @param username
     */
    private void updateLoginTimeByUsername(String username) {
        // 1 校验
        // 2 执行更新逻辑
        UmsAdmin admin = new UmsAdmin();
        admin.setLoginTime(new Date());
        UmsAdminExample adminExample = new UmsAdminExample();
        adminExample.createCriteria().andUsernameEqualTo(username);
        int count = adminMapper.updateByExampleSelective(admin, adminExample);
        // 3 返回结果集
    }

    /**
     * 添加登录日志
     * @param username
     */
    private void insertLoginLog(String username) {
        UmsAdmin admin = getByUsername(username);
        if (admin == null) {
            return;
        }
        UmsAdminLoginLog loginLog = new UmsAdminLoginLog();
        loginLog.setAdminId(admin.getId());
        loginLog.setCreatedTime(new Date());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        loginLog.setIp(request.getRemoteAddr());
        loginLogMapper.insert(loginLog);
    }
}
