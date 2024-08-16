package com.eldar.logistica.config.swagger;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi andreaniApi() {
        return GroupedOpenApi.builder()
                .group("Delivery")
                .packagesToScan("com.eldar.logistica.deliveryToEldar.controllers")
                .build();
    }

    @Bean
    public GroupedOpenApi providersApi() {
        return GroupedOpenApi.builder()
                .group("Providers")
                .packagesToScan("com.eldar.logistica.providers.controllers")
                .build();
    }


    @Bean
    public GroupedOpenApi terminalApi() {
        return GroupedOpenApi.builder()
                .group("Terminal")
                .packagesToScan("com.eldar.logistica.terminals.controllers")
                .build();
    }


    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Logistica API")
                        .version("1.0")
                        .description("API para Ver estado de envios de Andreani, Correo Argentino y OCA")
                        .contact(new Contact()
                                .name("Martin Colavita")
                                .email("martincolavita@eldars.com.ar")));
    }



}
