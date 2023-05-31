package com.zakander.stockpredictionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class StockPredictionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockPredictionServiceApplication.class, args);
	}

}
