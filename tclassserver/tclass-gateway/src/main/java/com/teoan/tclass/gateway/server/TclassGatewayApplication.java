package com.teoan.tclass.gateway.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Teoan
 * @description
 * @date 2021/5/16 21:50
 */
@SpringBootApplication
@EnableEurekaClient
public class TclassGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(TclassGatewayApplication.class,args);
    }
}
