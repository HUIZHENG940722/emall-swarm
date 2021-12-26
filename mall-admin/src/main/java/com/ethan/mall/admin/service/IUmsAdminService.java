package com.ethan.mall.admin.service;

import com.ethan.mall.admin.domain.UmsAdminRegisterParam;
import com.ethan.mall.model.UmsAdmin;

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
}
