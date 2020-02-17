package com.dksits.jke.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
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
@Import ( { springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration.class } )
public class SwaggerConfig {
    @Bean
    public Docket productApi () {
        return new Docket( DocumentationType.SWAGGER_2 ).select()
                .apis( RequestHandlerSelectors.any() )
                .apis( RequestHandlerSelectors.basePackage( "com.dksits.jke" ) )
                .paths( PathSelectors.any() )
                .build()
                .apiInfo( apiInfo() );
    }

    private ApiInfo apiInfo () {
        return new ApiInfo(
                "Servicio JKE MTVK",
                "Servicio de construccion y manejo de comando de llaves DES, 3DES con Base 24 HSM Thales",
                "API 1.0",
                "Terminos y condiciones",
                new Contact( "Gustavo Mancilla", "www.dksits.com", "gustavo@dksits.com" ),
                "License of API", "API license URL", Collections.emptyList() );
    }
}
