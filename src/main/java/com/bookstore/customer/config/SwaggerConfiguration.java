package com.bookstore.customer.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

	private static final String AUTHORIZATION = "Authorization";
	private static final String JWT = "JWT";

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.components(new Components().addSecuritySchemes(AUTHORIZATION, authorizationSecuritySchema()))
				.addSecurityItem(new SecurityRequirement().addList(AUTHORIZATION));
	}

	public SecurityScheme authorizationSecuritySchema() {
		return new SecurityScheme().name("bearerAuth").description(JWT).in(SecurityScheme.In.HEADER)
				.description(AUTHORIZATION).type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat(JWT);
	}

}