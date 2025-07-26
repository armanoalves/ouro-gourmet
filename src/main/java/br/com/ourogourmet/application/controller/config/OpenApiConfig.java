package br.com.ourogourmet.application.controller.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI ouroGourmet() {
        return new OpenAPI()
                .info(
                        new Info().title("Ouro-Gourmet API")
                                .description("Projeto desenvolvido durante a pós da FIAP")
                                .version("v0.0.1")
                                .license(new License().name("").url("https://github.com/armanoalves/ouro-gourmet"))
                );
    }
}

