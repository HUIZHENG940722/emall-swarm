package com.ethan.mall.auth.controller;

import com.ethan.mall.common.api.CommonData;
import com.ethan.mall.common.constant.AuthConstant;
import com.ethan.mall.common.domain.Oauth2TokenDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.Map;

/**
 * @author ethan
 * @Date 3:15 PM 2022/1/8
 * @Description 自定义Oauth2获取令牌接口
 */
@RestController
@Api(tags = "AuthController", description = "认证中心登录认证")
@RequestMapping(value = "/oauth")
public class AuthorizationController {
    @Resource
    private TokenEndpoint tokenEndpoint;

    @ApiOperation(value = "Oauth2获取token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "grant_type", value = "授权模式", required = true),
            @ApiImplicitParam(name = "client_id", value = "Oauth2客户端ID", required = true),
            @ApiImplicitParam(name = "client_secret", value = "Oauth2客户端密钥", required = true),
            @ApiImplicitParam(name = "refresh_token", value = "刷新token"),
            @ApiImplicitParam(name = "username", value = "登录用户名"),
            @ApiImplicitParam(name = "password", value = "登录密码")
    })
    @PostMapping(value = "/token")
    public CommonData<Oauth2TokenDto> postAccessToken(@ApiIgnore Principal principal,
                                                      @ApiIgnore
                                                      @RequestParam Map<String, String> params)
            throws HttpRequestMethodNotSupportedException {
        OAuth2AccessToken body = tokenEndpoint.postAccessToken(principal, params).getBody();
        assert body != null;
        Oauth2TokenDto oauth2Token = Oauth2TokenDto.builder()
                .token(body.getValue())
                .refreshToken(body.getRefreshToken().getValue())
                .expiresIn(body.getExpiresIn())
                .tokenHead(AuthConstant.JWT_TOKEN_PREFIX).build();
        return CommonData.success(oauth2Token);
    }
}
