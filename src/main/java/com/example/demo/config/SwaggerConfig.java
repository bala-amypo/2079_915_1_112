package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()

                // 🔒 DO NOT CHANGE — as requested
                .servers(List.of(
                        new Server().url("https://9093.408procr.amypo.ai/")
                ))

                // 📘 API METADATA (SAFE ADDITION)
                .info(new Info()
                        .title("Digital Credential Verification Engine")
                        .description("Swagger API documentation with JWT support")
                        .version("1.0"))

                // 🔐 GLOBAL JWT SECURITY
                .addSecurityItem(
                        new SecurityRequirement().addList("bearerAuth")
                )

                // 🔑 JWT SECURITY SCHEME
                .components(
                        new Components()
                                .addSecuritySchemes(
                                        "bearerAuth",
                                        new SecurityScheme()
                                                .name("Authorization")
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                                .in(SecurityScheme.In.HEADER)
                                )
                );
    }
}
