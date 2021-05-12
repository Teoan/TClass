package com.teoan.tclass.work.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author zhuangjy
 * @description 作业服务启动类
 * @date 2021/5/12 15:58
 */
@SpringBootApplication
@EnableEurekaClient
public class TclassWorkApplication {
    public static void main(String[] args) {
        SpringApplication.run(TclassWorkApplication.class,args);
    }
}
