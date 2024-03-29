package com.teoan.tclass.notice.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Teoan
 * @date 2021/5/11 22:39
 */
@SpringBootApplication
@ComponentScan("com.teoan.tclass")
@MapperScan("com.teoan.tclass.*.mapper")
@EnableFeignClients("com.teoan.tclass")
@EnableCaching
@EnableDiscoveryClient
public class TclassNoticeApplication {
    public static void main(String[] args) {
        SpringApplication.run(TclassNoticeApplication.class,args);
    }
}
