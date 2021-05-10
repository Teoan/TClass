package com.teoan.tclass.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author zhuangjy
 * @description tclass注册中心
 * @date 2021/5/8 17:34
 */
@EnableEurekaServer
@SpringBootApplication
public class TclassRegistryApplication {
    public static void main(String[] args) {
        SpringApplication.run(TclassRegistryApplication.class, args);
    }
}
