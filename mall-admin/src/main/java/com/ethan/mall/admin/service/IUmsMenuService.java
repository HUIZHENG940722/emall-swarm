package com.ethan.mall.admin.service;

import com.ethan.mall.model.UmsMenu;

import java.util.List;

/**
 * @author ethan
 * @Date 6:54 PM 2021/12/26
 * @Description 后台菜单管理业务接口
 */
public interface IUmsMenuService {
    /**
     * 分页获取父菜单下的菜单列表
     * @param parentId
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<UmsMenu> getList(Long parentId, Integer pageSize, Integer pageNum);
}
