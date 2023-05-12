package com.zakander.stockpredictionservice.service;

import org.springframework.stereotype.Service;

import com.zakander.stockpredictionservice.beans.StockModel;
import com.zakander.stockpredictionservice.repository.StockModelRepository;

@Service
public class StockModelService {
	private final StockModelRepository repository;

	public StockModelService(StockModelRepository repository) {
		this.repository = repository;
	}
	
	public StockModel findBySymbolAndNumDays(String symbol, int numDays) {
		return repository.findBySymbolAndNumDays(symbol, numDays);
	}
}
