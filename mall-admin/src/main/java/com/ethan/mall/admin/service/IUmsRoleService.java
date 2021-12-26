package com.ethan.mall.admin.service;

import com.ethan.mall.model.UmsRole;

import java.util.List;

/**
 * @author ethan
 * @Date 3:50 PM 2021/12/26
 * @Description 后台用户角色管理业务接口
 */
public interface IUmsRoleService {
    /**
     * 根据角色名称分页获取角色列表
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<UmsRole> getList(String keyword, Integer pageNum, Integer pageSize);

    /**
     * 添加角色
     * @param role
     * @return
     */
    int create(UmsRole role);
}
