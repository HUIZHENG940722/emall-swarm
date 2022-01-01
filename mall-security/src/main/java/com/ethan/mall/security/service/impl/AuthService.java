package com.ethan.mall.security.service.impl;

import com.ethan.mall.common.constant.AuthConstant;
import com.ethan.mall.common.domain.LoginUser;
import com.ethan.mall.security.constant.MessageConstant;
import com.ethan.mall.security.domain.AuthUser;
import com.ethan.mall.security.service.IUmsAdminService;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author ethan
 * @Date 9:51 PM 2021/12/29
 * @Description 认证业务类
 */
@Service
public class AuthService implements UserDetailsService {
    @Resource
    private IUmsAdminService adminService;
    @Resource
    private HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String username) {
        // 1 校验
        // 2 执行逻辑
        String clientId = request.getParameter("client_id");
        LoginUser loginUser = null;
        if(AuthConstant.ADMIN_CLIENT_ID.equals(clientId)){
            loginUser = adminService.loadUserByUsername(username);
        }
        if (loginUser == null) {
            throw new UsernameNotFoundException(MessageConstant.USERNAME_PASSWORD_ERROR);
        }
        loginUser.setClientId(clientId);
        AuthUser authUser = new AuthUser(loginUser);
        if (!authUser.isEnabled()) {
            throw new DisabledException(MessageConstant.ACCOUNT_DISABLED);
        } else if (!authUser.isAccountNonExpired()) {
            throw new LockedException(MessageConstant.ACCOUNT_LOCKED);
        } else if (!authUser.isAccountNonExpired()) {
            throw new AccountExpiredException(MessageConstant.ACCOUNT_EXPIRED);
        } else if (!authUser.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException(MessageConstant.CREDENTIALS_EXPIRED);
        }
        // 3 返回结果集
        return authUser;
    }
}
