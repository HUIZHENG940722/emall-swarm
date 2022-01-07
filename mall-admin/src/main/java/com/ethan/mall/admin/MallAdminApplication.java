package com.ethan.mall.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ethan
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.ethan.mall"})
@EnableDiscoveryClient
@EnableFeignClients
@EnableCaching
public class MallAdminApplication {
    static {
        System.setProperty("druid.mysql.usePingMethod", "false");
    }
    public static void main(String[] args) {
        SpringApplication.run(MallAdminApplication.class, args);
    }

}
