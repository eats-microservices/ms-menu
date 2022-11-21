package com.eats.msmenu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsMenuApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsMenuApplication.class, args);
	}

}
