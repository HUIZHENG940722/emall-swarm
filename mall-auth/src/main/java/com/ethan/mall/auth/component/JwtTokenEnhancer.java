package com.ethan.mall.auth.component;

import com.ethan.mall.auth.domain.AuthorizationUser;
import com.ethan.mall.common.domain.LoginUser;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ethan
 * @Date 9:41 AM 2022/1/8
 * @Description JWT内容增强器
 */
@Component
public class JwtTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        AuthorizationUser authorizationUser = (AuthorizationUser) authentication.getPrincipal();
        // 把用户信息设置到JWT中
        Map<String, Object> info = new HashMap<>(10);
        info.put("id", authorizationUser.getLoginUser().getId());
        info.put("client_id", authorizationUser.getLoginUser().getClientId());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
        return accessToken;
    }
}
