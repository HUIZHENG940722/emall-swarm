package com.ethan.mall.gateway.authorization;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.ethan.mall.common.constant.AuthConstant;
import com.ethan.mall.gateway.config.IgnoreUrlsConfig;
import com.nimbusds.jose.JWSObject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.net.URI;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author ethan
 * @Date 6:04 PM 2022/1/8
 * @Description 鉴权管理器，用于判断是否有资源的访问权限
 */
@Component
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private IgnoreUrlsConfig ignoreUrlsConfig;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication,
                                             AuthorizationContext object) {
        ServerHttpRequest request = object.getExchange().getRequest();
        URI uri = request.getURI();
        PathMatcher pathMatcher = new AntPathMatcher();
        // 白名单直接放行
        List<String> ignoreUrls = ignoreUrlsConfig.getUrls();
        for (String ignoreUrl : ignoreUrls) {
            if (pathMatcher.match(ignoreUrl, uri.getPath())) {
                return Mono.just(new AuthorizationDecision(true));
            }
        }
        // 对应跨域的预检请求直接放行
        if (Objects.equals(request.getMethod(), HttpMethod.OPTIONS)) {
            return Mono.just(new AuthorizationDecision(true));
        }
        // 不同用户体系不允许互相访问
        Map info = null;
        try {
            String token = request.getHeaders().getFirst(AuthConstant.JWT_TOKEN_HEADER);
            if (StrUtil.isBlank(token)) {
                return Mono.just(new AuthorizationDecision(false));
            }
            String realToken = token.replace(AuthConstant.JWT_TOKEN_PREFIX, "");
            JWSObject jwsObject = JWSObject.parse(realToken);
            String userStr = jwsObject.getPayload().toString();
            info = JSONUtil.toBean(userStr, Map.class);
            if (AuthConstant.ADMIN_CLIENT_ID.equals(info.get("client_id")) &&
                    !pathMatcher.match(AuthConstant.ADMIN_URL_PATTERN, uri.getPath())) {
                return Mono.just(new AuthorizationDecision(false));
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return Mono.just(new AuthorizationDecision(false));
        }
        // 非管理端路径直接放行
        if (!pathMatcher.match(AuthConstant.ADMIN_URL_PATTERN, uri.getPath())) {
            return Mono.just(new AuthorizationDecision(true));
        }
        // 管理端路径需要校验权限
        JSONArray jsonArray = (JSONArray) info.get("authorities");
        Object map = jsonArray.get(0);
        if (map == null) {
            return Mono.just(new AuthorizationDecision(false));
        }
        return Mono.just(new AuthorizationDecision(true));
        /*Map<Object,Object> resourceRoleMap = redisTemplate.opsForHash()
                .entries(AuthConstant.RESOURCE_ROLES_MAP_KEY);
        Iterator<Object> iterator = resourceRoleMap.keySet().iterator();
        List<String> authorities = new ArrayList<>();
        while (iterator.hasNext()) {
            String pattern = (String) iterator.next();
            if (pathMatcher.match(pattern, uri.getPath())) {
                authorities.addAll(Convert.toList(String.class, resourceRoleMap.get(pattern)));
            }
        }
        authorities = authorities.stream().map(i ->
                i = AuthConstant.AUTHORITY_PREFIX + i).collect(Collectors.toList());
        // 认证通过且角色匹配的用户可以匹配当前路径
        return authentication.filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(authorities::contains)
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(false));*/
    }
}
