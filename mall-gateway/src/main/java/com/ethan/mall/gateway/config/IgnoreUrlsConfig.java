package com.ethan.mall.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ethan
 * @Date 5:50 PM 2022/1/8
 * @Description 网关白名单配置
 */
@Data
@Component
@ConfigurationProperties(value = "secure.ignore")
public class IgnoreUrlsConfig {
    private List<String> urls;
}
