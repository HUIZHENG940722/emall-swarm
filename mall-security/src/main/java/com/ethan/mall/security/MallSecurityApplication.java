package com.ethan.mall.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author ethan
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class MallSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallSecurityApplication.class, args);
    }

}
