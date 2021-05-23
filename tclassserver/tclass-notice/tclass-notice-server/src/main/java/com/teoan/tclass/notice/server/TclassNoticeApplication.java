package com.teoan.tclass.notice.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Teoan
 * @date 2021/5/11 22:39
 */
@SpringBootApplication
@ComponentScan("com.teoan.tclass")
@MapperScan("com.teoan.tclass.notice.mapper")
@EnableFeignClients("com.teoan.tclass")
@EnableEurekaClient
public class TclassNoticeApplication {
    public static void main(String[] args) {
        SpringApplication.run(TclassNoticeApplication.class,args);
    }
}
