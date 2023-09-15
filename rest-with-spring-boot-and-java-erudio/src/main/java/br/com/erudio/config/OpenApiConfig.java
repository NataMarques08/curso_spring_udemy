package br.com.erudio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenApi() {
		return new OpenAPI()
				.info(new Info()
						.title("RESTful API WITH JAVA 17 AND SPRING BOOT 3")
						.version("v1")
						.description("SPRING BOOT")
						.termsOfService("www.nata.com/licensa")
						.license(new License()
								.name("Apache 2.0")
								.url("www.nata.com")));
	}
}
