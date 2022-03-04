package com.ethan.mall.auth.service.impl;

import com.ethan.mall.auth.constant.MessageConstant;
import com.ethan.mall.auth.domain.AuthorizationUser;
import com.ethan.mall.auth.service.feign.IUmsAdminService;
import com.ethan.mall.common.constant.AuthConstant;
import com.ethan.mall.common.domain.LoginUser;
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
 * @Date 9:38 AM 2022/1/8
 * @Description 认证业务类
 */
@Service
public class AuthorizationService implements UserDetailsService {
    @Resource
    private IUmsAdminService adminService;
    @Resource
    private HttpServletRequest request;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String clientId = request.getParameter("client_id");
        LoginUser loginUser = null;
        if (AuthConstant.ADMIN_CLIENT_ID.equals(clientId)) {
            loginUser = adminService.loadUserByUsername(username);
        } else {
            System.out.println("执行其他逻辑");
        }
        if (loginUser == null) {
            throw new UsernameNotFoundException(MessageConstant.USERNAME_PASSWORD_ERROR);
        }
        loginUser.setClientId(clientId);
        AuthorizationUser authorizationUser = new AuthorizationUser(loginUser);
        if (!authorizationUser.isEnabled()) {
            throw new DisabledException(MessageConstant.ACCOUNT_DISABLED);
        } else if (!authorizationUser.isAccountNonExpired()) {
            throw new LockedException(MessageConstant.ACCOUNT_LOCKED);
        } else if (!authorizationUser.isAccountNonExpired()) {
            throw new AccountExpiredException(MessageConstant.ACCOUNT_EXPIRED);
        } else if (!authorizationUser.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException(MessageConstant.CREDENTIALS_EXPIRED);
        }
        return authorizationUser;
    }
}
