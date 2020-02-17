package us.gonet.balance.config;

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
                .apis( RequestHandlerSelectors.basePackage( "us.gonet.balance" ) )
                .paths( PathSelectors.any() )
                .build()
                .apiInfo( apiInfo() );
    }

    private ApiInfo apiInfo () {
        return new ApiInfo(
                "Servicios de consulta de saldo ATM",
                "Servicio de construccion y envio de mensajeria ISO-8583 para transacciones de retiro de efectivo",
                "API 1.0",
                "Terminos y condiciones",
                new Contact( "Gustavo Mancilla", "www.dksits.com", "gustavo@dksits.com" ),
                "License of API", "API license URL", Collections.emptyList() );
    }
}
