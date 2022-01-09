package com.ethan.mall.security.domain;

import com.ethan.mall.common.domain.LoginUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author ethan
 * @Date 8:43 AM 2021/12/30
 * @Description 认证用户信息
 */
public class AuthUser implements UserDetails {
    private LoginUser loginUser;

    public AuthUser(LoginUser loginUser) {
        this.loginUser = loginUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        if (loginUser.getRoles() != null) {
            loginUser.getRoles().forEach(item -> authorities.add(new SimpleGrantedAuthority(item)));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return loginUser.getPassword();
    }

    @Override
    public String getUsername() {
        return loginUser.getUsername();
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
        return loginUser.getStatus()==1;
    }
}
