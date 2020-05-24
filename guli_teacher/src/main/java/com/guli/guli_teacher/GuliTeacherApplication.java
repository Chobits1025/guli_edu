package com.guli.guli_teacher;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.guli.guli_teacher.mapper")
@SpringBootApplication
public class GuliTeacherApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuliTeacherApplication.class, args);
    }

}
