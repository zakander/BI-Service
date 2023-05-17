package com.zakander.stockpredictionservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.zakander.stockpredictionservice.beans.StockModel;
import com.zakander.stockpredictionservice.beans.StockPredictionsBean;
import com.zakander.stockpredictionservice.scraper.Scraper;

@RestController
public class StockModelController {

	@GetMapping("/stock-prediction/{symbol}/{numDays}/{modelType}")
	public StockPredictionsBean retrievePredictions(
			@PathVariable String symbol,
			@PathVariable int numDays,
			@PathVariable String modelType) {
		
		StockModel model = new StockModel(1000L, symbol, numDays);
		String[] predictions = model.getPredictions(symbol, numDays, modelType);
		return new StockPredictionsBean(predictions);
	}
	
	@GetMapping("/date-data/{symbol}/{dateStr}")
	public StockPredictionsBean retrieveData(
			@PathVariable String symbol,
			@PathVariable String dateStr) {
		
		String[] data = Scraper.scrapeDate(symbol, dateStr);
		return new StockPredictionsBean(data);
	}
}
