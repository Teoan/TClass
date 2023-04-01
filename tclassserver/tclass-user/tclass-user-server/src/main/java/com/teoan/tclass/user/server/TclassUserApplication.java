package com.teoan.tclass.user.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Teoan
 * @description 用户微服务启动类
 * @date 2021/5/12 15:51
 */
@SpringBootApplication
@ComponentScan("com.teoan.tclass")
@MapperScan("com.teoan.tclass.*.mapper")
@EnableFeignClients("com.teoan.tclass")
@EnableCaching
@EnableDiscoveryClient
public class TclassUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(TclassUserApplication.class,args);
    }
}
