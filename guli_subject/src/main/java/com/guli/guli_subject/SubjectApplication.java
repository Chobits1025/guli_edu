package com.guli.guli_subject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@MapperScan(basePackages = "com.guli.guli_subject.mapper")
public class SubjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(SubjectApplication.class,args);
    }
}
