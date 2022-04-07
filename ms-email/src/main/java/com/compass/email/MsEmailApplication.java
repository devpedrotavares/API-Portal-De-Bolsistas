package com.compass.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;

@LoadBalancerClient(name = "portalcompass")
@SpringBootApplication
public class MsEmailApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsEmailApplication.class, args);
	}

}
