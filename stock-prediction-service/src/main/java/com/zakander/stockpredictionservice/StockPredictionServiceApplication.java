package com.zakander.stockpredictionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.zakander.stockpredictionservice"})
@EnableJpaRepositories
public class StockPredictionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockPredictionServiceApplication.class, args);
	}

}
