package com.ethan.mall.admin.dao;

import com.ethan.mall.model.UmsMenu;

import java.util.List;

/**
 * @author ethan
 * @Date 11:36 AM 2022/1/3
 * @Description 后台用户数据访问扩展
 */
public interface UmsAdminDao {

    /**
     * 根据用户获取菜单列表
     * @param adminId
     * @return
     */
    List<UmsMenu> getMenuList(Long adminId);
}
