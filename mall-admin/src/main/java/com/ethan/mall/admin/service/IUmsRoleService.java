package com.ethan.mall.admin.service;

import com.ethan.mall.model.UmsMenu;
import com.ethan.mall.model.UmsResource;
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

    /**
     * 更新角色信息
     * @param id
     * @param role
     * @return
     */
    int update(Long id, UmsRole role);

    /**
     * 给角色分配菜单
     * @param roleId
     * @param menuIds
     * @return
     */
    int allocMenu(Long roleId, List<Long> menuIds);

    /**
     * 获取角色对应的菜单列表
     * @param roleId
     * @return
     */
    List<UmsMenu> menuList(Long roleId);

    /**
     * 获取角色相关的资源列表
     * @param roleId
     * @return
     */
    List<UmsResource> listResources(Long roleId);

    /**
     * 给角色分配资源
     * @param roleId
     * @param resourceIds
     * @return
     */
    int allocResource(Long roleId, List<Long> resourceIds);

    /**
     * 获取所有的角色列表
     * @return
     */
    List<UmsRole> listAll();
}
