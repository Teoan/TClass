package com.teoan.tclass.notice.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Teoan
 * @description
 * @date 2021/5/11 22:39
 */
@SpringBootApplication
@EnableEurekaClient
public class TclassNoticeApplication {
    public static void main(String[] args) {
        SpringApplication.run(TclassNoticeApplication.class,args);
    }
}
