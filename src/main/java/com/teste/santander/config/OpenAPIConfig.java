package com.teste.santander.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

	@Bean
	OpenAPI openAPI() {
		Server devServer = new Server();
		devServer.setUrl("http://localhost:8080");
		devServer.setDescription("Server URL in Development environment");

		Contact contact = new Contact();
		contact.setEmail("emersondiaspd@gmail.com");
		contact.setName("Emerson Dias de Oliveira");

		Info info = new Info()
				.title("Teste Desenvolvedor Backend")
				.version("1.0")
				.contact(contact)
				.description("Desafio para desenvolvedor backend Java do Banco Santander.");

		return new OpenAPI().info(info).servers(List.of(devServer));
	}

}
