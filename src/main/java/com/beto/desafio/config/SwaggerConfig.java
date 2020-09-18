package com.beto.desafio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.beto.desafio.controllers"))
                .paths(PathSelectors.any())
                .build()
                .groupName("Alberto Lopes Developer")
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Desafio API",
                "Esta API é utilizada para controlar requisições REST",
                "Versão beta-1.0",
                "",
                new Contact("Alberto Lopes", "https://github.com/albertolopes", "albertolopes@mail.com"),
                "",
                "",
                Collections.emptyList() // Vendor Extensions
        );
    }
}
