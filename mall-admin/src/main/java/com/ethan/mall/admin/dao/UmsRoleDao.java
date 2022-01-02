package com.ethan.mall.admin.dao;

import com.ethan.mall.model.UmsMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ethan
 * @Date 2:42 PM 2022/1/2
 * @Description 用户角色数据访问扩展
 */
public interface UmsRoleDao {
    /**
     * 根据角色id获取菜单列表
     * @param roleId
     * @return
     */
    List<UmsMenu> getMenuListByRoleId(@Param(value = "roleId") Long roleId);
}
