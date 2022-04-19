package com.compass.portalcompass;

import org.modelmapper.ModelMapper;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@EnableSwagger2
@EnableCaching
public class PortalcompassApplication {
	
	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(PortalcompassApplication.class, args);
		
		
	}

}
