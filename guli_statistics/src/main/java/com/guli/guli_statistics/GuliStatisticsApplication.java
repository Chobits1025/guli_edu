package com.guli.guli_statistics;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan(basePackages = "com.guli.guli_statistics.mapper")
@EnableSwagger2
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.guli.guli_statistics.service.feign")
public class GuliStatisticsApplication {
    public static void main(String[] args) {
        SpringApplication.run(GuliStatisticsApplication.class,args);
    }
}
