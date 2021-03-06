package com.teoan.tclass.work.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author Teoan
 * @description 作业服务启动类
 * @date 2021/5/12 15:58
 */
@SpringBootApplication
@ComponentScan("com.teoan.tclass")
@MapperScan("com.teoan.tclass.*.mapper")
@EnableCaching
@EnableResourceServer
@EnableFeignClients("com.teoan.tclass")
@EnableEurekaClient
public class TclassWorkApplication {
    public static void main(String[] args) {
        SpringApplication.run(TclassWorkApplication.class,args);
    }
}
