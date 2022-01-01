package com.ethan.mall.security.controller;

import com.ethan.mall.security.component.JwtTokenUtil;
import com.ethan.mall.security.service.impl.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ethan
 * @Date 12:12 PM 2022/1/1
 * @Description token控制器
 */
@RestController
@Api(tags = "TokenController", description = "token控制器")
@RequestMapping(value = "/token")
public class TokenController {
    @Resource
    private AuthService authService;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping(value = "/str")
    @ApiOperation(value = "获取token字符串")
    public String getToken(@RequestParam String username, @RequestParam String password) {
        UserDetails userDetails = authService.loadUserByUsername(username);
        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("密码不正确");
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
                null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }
}
