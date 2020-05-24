package com.guli.guli_ucenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan(basePackages = "com.guli.guli_ucenter.mapper")
@EnableSwagger2
@CrossOrigin
@EnableEurekaClient
public class GuliUCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(GuliUCenterApplication.class,args);
    }
}
