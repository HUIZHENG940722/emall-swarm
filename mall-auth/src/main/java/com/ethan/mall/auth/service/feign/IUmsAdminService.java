package com.ethan.mall.auth.service.feign;

import com.ethan.mall.common.domain.LoginUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ethan
 * @Date 10:27 AM 2022/1/8
 * @Description 后台管理员认证业务类
 */
@FeignClient(value = "mall-admin")
public interface IUmsAdminService {
    /**
     * 根据用户名获取登录用户信息
     * @param username
     * @return
     */
    @GetMapping(value = "/admin/loadByUsername")
    LoginUser loadUserByUsername(@RequestParam(value = "username") String username);
}
