package com.guli.guli_teacher.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootConfiguration
public class Swagger2Config {

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(webApiInfo())
                .select()
                .build();
    }

    private ApiInfo webApiInfo(){

        return new ApiInfoBuilder()
                .title("网站-讲师API文档")
                .description("本文档描述了讲师服务接口定义")
                .version("1.0")
                .contact(new Contact("大桥未久", "https://javbus.com", "do_hon@qq.com"))
                .build();
    }

}
