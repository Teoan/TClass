package com.teoan.tclass.gateway.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Teoan
 * @description
 * @date 2021/5/16 21:50
 */
@SpringBootApplication
@EnableDiscoveryClient
public class TclassGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(TclassGatewayApplication.class,args);
    }
}
