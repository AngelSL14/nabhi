package us.gonet.rcpt.config;

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
                .apis( RequestHandlerSelectors.basePackage( "us.gonet.rcpt" ) )
                .paths( PathSelectors.any() )
                .build()
                .apiInfo( apiInfo() );
    }

    private ApiInfo apiInfo () {
        return new ApiInfo(
                "Servicios de Tickets",
                "Servicio de construccion y envio de Tickets transaccionales",
                "API 1.0",
                "Terminos y condiciones",
                new Contact( "Gustavo Mancilla", "www.gonet.us", "gustavo.mancilla@gonet.us" ),
                "License of API", "API license URL", Collections.emptyList() );
    }
}
