package com.ethan.mall.admin.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author ethan
 * @Date 2:21 下午 2021/11/28
 * @Description Mybatis配置
 */
@Configuration
@MapperScan(basePackages = {"com.ethan.mall.mapper", "com.ethan.mall.admin.dao"})
public class MybatisConfig {
}
