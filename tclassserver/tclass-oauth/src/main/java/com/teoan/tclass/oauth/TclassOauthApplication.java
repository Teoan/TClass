package com.teoan.tclass.oauth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Teoan
 * @description 认证服务应用
 * @date 2021/5/25 11:34
 */
@SpringBootApplication
@ComponentScan("com.teoan.tclass")
@MapperScan("com.teoan.tclass.common.mapper")
@EnableDiscoveryClient
public class TclassOauthApplication {
    public static void main(String[] args) {
        SpringApplication.run(TclassOauthApplication.class);
    }
}
