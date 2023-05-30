package com.zakander.stockpredictionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.zakander.stockscraperservice.repository")
@EnableAutoConfiguration
public class StockPredictionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockPredictionServiceApplication.class, args);
	}

}
