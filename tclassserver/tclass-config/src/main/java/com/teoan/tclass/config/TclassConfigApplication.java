package com.teoan.tclass.config;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author zhuangjy
 * @description Tclass配置中心
 * @date 2021/5/14 9:49
 */
@SpringCloudApplication
@EnableConfigServer
public class TclassConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(TclassConfigApplication.class, args);
    }
}
