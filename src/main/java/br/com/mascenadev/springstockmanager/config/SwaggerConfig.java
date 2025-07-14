package br.com.mascenadev.springstockmanager.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Value("${springdoc.api.version}")
    private String apiVersion;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("Spring Stock Manager")
                .version(apiVersion)
                .description("API for managing stock in a Spring application")
                .license(apiLicense()));
    }

    private License apiLicense() {
        return new License()
                .name("MIT License")
                .url("https://opensource.org/license/mit/");
    }
}
