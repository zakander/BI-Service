package com.zakander.stockscraperservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class StockScraperServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockScraperServiceApplication.class, args);
	}

}
