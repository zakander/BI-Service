package com.zakander.stockscraperservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.zakander.stockscraperservice.repository")
@EnableAutoConfiguration
public class StockScraperServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockScraperServiceApplication.class, args);
	}

}
