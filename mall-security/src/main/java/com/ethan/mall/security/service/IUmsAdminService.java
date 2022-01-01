package com.ethan.mall.security.service;

import com.ethan.mall.common.domain.LoginUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ethan
 * @Date 8:31 AM 2021/12/30
 * @Description 后台用户认证业务类
 */
@FeignClient(name = "mall-admin")
public interface IUmsAdminService {
    /**
     * 根据用户名获取登录用户信息
     * @param username
     * @return
     */
    @GetMapping(value = "/admin/loadByUsername")
    LoginUser loadUserByUsername(@RequestParam(value = "username") String username);
}
