package com.ethan.mall.admin.service;

import com.ethan.mall.admin.domain.UmsAdminRegisterParam;
import com.ethan.mall.common.api.CommonData;
import com.ethan.mall.common.domain.LoginUser;
import com.ethan.mall.model.UmsAdmin;
import com.ethan.mall.model.UmsMenu;
import com.ethan.mall.model.UmsRole;

import java.util.List;

/**
 * @author ethan
 * @Date 11:50 上午 2021/11/28
 * @Description 后台用户管理业务接口
 */
public interface IUmsAdminService {
    /**
     * 注册后台用户
     * @param adminRegisterParam
     * @return
     */
    UmsAdmin register(UmsAdminRegisterParam adminRegisterParam);

    /**
     * 根据用户账号或名称分页获取用户列表
     * @param keyword
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<UmsAdmin> getList(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 更新指定用户信息
     * @param id
     * @param adminRegisterParam
     * @return
     */
    int update(Long id, UmsAdminRegisterParam adminRegisterParam);


    /**
     * 根据用户名获取后台用户
     * @param username
     * @return
     */
    UmsAdmin getByUsername(String username);

    /**
     * 根据后台用户id获取角色列表
     * @param adminId
     * @return
     */
    List<UmsRole> getRoleList(Long adminId);

    /**
     * 根据用户名获取登录用户信息
     * @param username
     * @return
     */
    LoginUser loadUserByUsername(String username);

    /**
     * 用户登录获取token
     * @param username
     * @param password
     * @return
     */
//    String login(String username, String password);

    /**
     * 给用户分配角色
     * @param adminId
     * @param roleIds
     * @return
     */
    int updateRole(Long adminId, List<Long> roleIds);

    /**
     * 获取当前登录用户信息
     * @param principal
     * @return
     */
//    Map getAdminInfo(Principal principal);

    /**
     * 获取用户菜单列表
     * @param adminId
     * @return
     */
    List<UmsMenu> getMenuList(Long adminId);

    /**
     * 根据后台用户id获取后台用户
     * @param id
     * @return
     */
    UmsAdmin getByAdminId(Long id);

    /**
     * 登录以后返回token
     * @param username
     * @param password
     * @return
     */
    CommonData login(String username, String password);
}
