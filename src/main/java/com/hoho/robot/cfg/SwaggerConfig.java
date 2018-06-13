package com.hoho.robot.cfg;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerConfig
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {


    @Bean
    public Docket demoApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hoho.robot.web"))
                .build()
                .apiInfo(demoApiInfo());
    }

    private ApiInfo demoApiInfo() {
        Contact contact = new Contact("wanlf", "https://github.com/longfeiwan1566/robot.git", "505694810@qq.com");
        ApiInfo apiInfo = new ApiInfo("ROBOT API",//大标题
                "robot",//小标题
                "1.0",//版本
                "NO terms of service",
                contact,//作者
                "查看源代码",//链接显示文字
                "https://github.com/longfeiwan1566/robot.git"//网站链接
        );
        return apiInfo;
    }
}