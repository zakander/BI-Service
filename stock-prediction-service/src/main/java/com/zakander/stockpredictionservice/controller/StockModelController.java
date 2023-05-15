package com.zakander.stockpredictionservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.zakander.stockpredictionservice.beans.StockModel;
import com.zakander.stockpredictionservice.beans.StockPredictionsBean;
import com.zakander.stockpredictionservice.scraper.Scraper.StockType;
import com.zakander.stockpredictionservice.service.StockModelService;

@RestController
public class StockModelController {
	
	@Autowired
	private final StockModelService service;
	
	public StockModelController(StockModelService service) {
		this.service = service;
	}

	@GetMapping("/stock-prediction/{symbol}/{numDays}")
	public StockPredictionsBean retrieveModel(
			@PathVariable String symbol,
			@PathVariable int numDays) {
		
		StockModel model = new StockModel(1000L, symbol, numDays);
		String[] predictions = model.getPredictions(symbol, numDays);
		return new StockPredictionsBean(predictions);
	}
}
