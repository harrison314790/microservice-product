package com.microservice.product.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI productMicroserviceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Product Microservice API")
                        .description("API para gestión de productos")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Harrison Ineey valencia Otero")
                                .email("harrison.valencia@correounivalle.edu.co"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }
}
