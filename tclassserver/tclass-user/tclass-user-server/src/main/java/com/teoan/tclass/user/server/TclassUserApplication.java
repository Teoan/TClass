package com.teoan.tclass.user.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zhuangjy
 * @description 用户微服务启动类
 * @date 2021/5/12 15:51
 */
@SpringBootApplication
@ComponentScan("com.teoan.tclass")
@EnableEurekaClient
public class TclassUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(TclassUserApplication.class,args);
    }
}
