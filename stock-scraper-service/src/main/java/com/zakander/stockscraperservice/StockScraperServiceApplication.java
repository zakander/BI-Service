package com.zakander.stockscraperservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.zakander.stockscraperservice.repository")
@EnableAutoConfiguration
@EntityScan("com.zakander.stockscraperservice.entities")
public class StockScraperServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockScraperServiceApplication.class, args);
	}

}
