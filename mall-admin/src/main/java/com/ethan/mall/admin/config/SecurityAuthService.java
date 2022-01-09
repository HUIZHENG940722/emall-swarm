//package com.ethan.mall.admin.config;
//
//import com.ethan.mall.auth.AuthUser;
//import com.ethan.mall.admin.service.IUmsAdminService;
//import com.ethan.mall.common.domain.LoginUser;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//
///**
// * @author ethan
// * @Date 9:51 PM 2021/12/29
// * @Description 认证业务类
// */
//@Service
//public class SecurityAuthService implements UserDetailsService {
//    @Resource
//    private IUmsAdminService adminService;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) {
//        LoginUser loginUser = adminService.loadUserByUsername(username);
//        if (loginUser == null) {
//            throw new UsernameNotFoundException("用户名或密码错误");
//        }
//        return new AuthUser(loginUser);
//    }
//}
