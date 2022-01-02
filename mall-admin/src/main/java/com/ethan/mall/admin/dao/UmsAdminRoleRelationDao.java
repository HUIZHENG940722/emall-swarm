package com.ethan.mall.admin.dao;

import com.ethan.mall.model.UmsAdminRoleRelation;
import com.ethan.mall.model.UmsRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ethan
 * @Date 10:08 PM 2021/12/30
 * @Description 后台用户角色数据访问扩展
 */
public interface UmsAdminRoleRelationDao {

    /**
     * 根据后台用户id获取角色列表
     * @param adminId
     * @return
     */
    List<UmsRole> getRoleList(@Param(value = "adminId") Long adminId);

    /**
     * 批量插入用户角色关联数据
     * @param list
     */
    void insertList(List<UmsAdminRoleRelation> list);
}
