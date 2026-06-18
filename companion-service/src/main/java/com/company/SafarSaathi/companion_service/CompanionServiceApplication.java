package com.company.SafarSaathi.companion_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableFeignClients
public class CompanionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanionServiceApplication.class, args);
	}

}
