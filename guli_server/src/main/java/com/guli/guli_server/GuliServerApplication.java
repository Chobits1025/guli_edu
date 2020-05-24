package com.guli.guli_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class GuliServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(GuliServerApplication.class,args);
    }
}
