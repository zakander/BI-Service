package com.zakander.stockdatedataservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class StockDateDataServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockDateDataServiceApplication.class, args);
	}

}
