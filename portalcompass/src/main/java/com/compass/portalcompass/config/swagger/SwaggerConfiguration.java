package com.compass.portalcompass.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.compass.portalcompass"))
				.paths(PathSelectors.ant("/**"))
				.build()
				.apiInfo(appInfo());
	}
	
	private ApiInfo appInfo() {
		return new ApiInfoBuilder()
				.title("Api Rest Compass Notas dos alunos")
				.description("Avaliação Sprint 5 e 6."
						+ "\n Time de Desenvolvimento"
						+ "\n Carlos , https://github.com/Carlos-Shinra-Neto"
						+ "\n Matheus , https://github.com/Matheus-Braynner"
						+ "\n Pedro , https://github.com/devpedrotavares"
						+ "\n Vinicius , https://github.com/Vinicius-Muliterno")
				.version("0.0.1")
				.license("Apache Licence Version 3.0")
				.contact(new Contact("Repositório","https://github.com/Carlos-Shinra-Neto/Sprint-5-Api",""))
				.build();
	}
}