package com.ethan.mall.auth.config;

import com.ethan.mall.common.config.BaseSwaggerConfig;
import com.ethan.mall.common.domain.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author ethan
 * @Date 7:51 下午 2021/11/27
 * @Description Swagger API文档相关配置
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {
    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.ethan.mall.admin.controller")
                .title("mall后台系统")
                .description("mall后台相关接口文档")
                .contactName("ethan")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
