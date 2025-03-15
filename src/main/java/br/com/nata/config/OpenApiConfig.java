package br.com.nata.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenAPI(){
        return new OpenAPI()
            .info(new Info().title("RestAPI's Resful from 0 with Java, Spring Boot, Kubernates and Docker")
            .version("v1.0")
            .description("RestAPI's Resful from 0 with Java, Spring Boot, Kubernates and Docker")
            .termsOfService("https://github.com/NataMarques08")
            .license(new License()
            .name("GNU General Public License v3.0")
            .url("https://www.gnu.org/licenses/gpl-3.0.html"))
        );
    }
    
}
