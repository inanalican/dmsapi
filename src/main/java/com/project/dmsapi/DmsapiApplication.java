package com.project.dmsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "com.project.dmsapi.repository")
public class DmsapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DmsapiApplication.class, args);
	}
}
