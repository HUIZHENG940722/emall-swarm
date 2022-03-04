package com.ethan.mall.gateway.filter;

import com.ethan.mall.common.constant.AuthConstant;
import com.ethan.mall.gateway.config.IgnoreUrlsConfig;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author ethan
 * @Date 4:00 PM 2022/1/9
 * @Description 白名单路径访问时需要移除JWT请求头
 */
@Component
public class IgnoreUrlsRemoveJwtFilter implements WebFilter {
    @Resource
    private IgnoreUrlsConfig ignoreUrlsConfig;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        URI uri = request.getURI();
        PathMatcher pathMatcher = new AntPathMatcher();
        // 白名单路径移除JWT请求头
        List<String> urls = ignoreUrlsConfig.getUrls();
        for (String url : urls) {
            if (pathMatcher.match(url, uri.getPath())) {
                request = exchange.getRequest().mutate().header(AuthConstant
                        .JWT_TOKEN_HEADER, "").build();
                exchange.mutate().request(request).build();
            }
        }
        return chain.filter(exchange);
    }
}
