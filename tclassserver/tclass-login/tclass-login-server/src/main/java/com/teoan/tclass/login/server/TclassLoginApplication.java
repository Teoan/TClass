package com.teoan.tclass.login.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zhuangjy
 * @description 登录服务
 * @date 2021/5/10 14:58
 */
@EnableEurekaClient
@ComponentScan("com.teoan.tclass")
@SpringBootApplication
public class TclassLoginApplication {
    public static void main(String[] args) {
        SpringApplication.run(TclassLoginApplication.class, args);
    }
}
