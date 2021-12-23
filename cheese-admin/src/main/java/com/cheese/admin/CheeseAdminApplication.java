package com.cheese.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.cheese.domain"})
@EnableJpaRepositories(basePackages = {"com.cheese.domain"})
@SpringBootApplication
public class CheeseAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(CheeseAdminApplication.class, args);
	}

}
