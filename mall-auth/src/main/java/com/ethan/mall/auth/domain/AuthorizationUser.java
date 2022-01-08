package com.ethan.mall.auth.domain;

import cn.hutool.core.collection.CollUtil;
import com.ethan.mall.common.domain.LoginUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ethan
 * @Date 11:15 AM 2022/1/8
 * @Description 认证用户
 */
public class AuthorizationUser implements UserDetails {
    private LoginUser loginUser;

    public AuthorizationUser(LoginUser loginUser) {
        this.loginUser = loginUser;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> roles = this.loginUser.getRoles();
        if (CollUtil.isEmpty(roles)) {
            return null;
        }
        return roles.stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.loginUser.getPassword();
    }

    @Override
    public String getUsername() {
        return this.loginUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.loginUser.getStatus().equals(1);
    }
}
