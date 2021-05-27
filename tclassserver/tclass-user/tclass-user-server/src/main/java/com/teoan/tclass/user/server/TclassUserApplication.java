package com.teoan.tclass.user.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author zhuangjy
 * @description 用户微服务启动类
 * @date 2021/5/12 15:51
 */
@SpringBootApplication
@ComponentScan("com.teoan.tclass")
@MapperScan("com.teoan.tclass.user.mapper")
@EnableFeignClients("com.teoan.tclass")
@EnableResourceServer
@EnableCaching
@EnableEurekaClient
public class TclassUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(TclassUserApplication.class,args);
    }
}
