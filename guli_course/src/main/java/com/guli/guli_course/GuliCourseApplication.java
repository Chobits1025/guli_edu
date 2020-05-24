package com.guli.guli_course;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan(basePackages = "com.guli.guli_course.mapper")
@EnableTransactionManagement
@EnableSwagger2
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.guli.guli_course.service.feign")
public class GuliCourseApplication {
    public static void main(String[] args) {
        SpringApplication.run(GuliCourseApplication.class,args);
    }
}
