package com.zakander.stockpredictionservice.controller;

import java.time.LocalDate;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zakander.stockpredictionservice.entities.Post;
import com.zakander.stockpredictionservice.entities.StockPredictionsData;
import com.zakander.stockpredictionservice.predictionmodel.PredictionModel;
import com.zakander.stockpredictionservice.repository.DataRepository;

import jakarta.validation.Valid;

@RestController
public class Controller {
	@Autowired
	private DataRepository repository;
	
	@PostMapping("/make-prediction")
	public StockPredictionsData createPredictions(@Valid @RequestBody Post post) {
		
		TreeMap<LocalDate, String[]> historicalData = repository.getHistoricalData(
				post.getSymbol(), post.getNumDays());
		
		String[] predictionValues = PredictionModel.getPredictions(
									post.getSymbol(),
									post.getNumDays(),
									post.getModelType(),
									historicalData);
		
		StockPredictionsData data = new StockPredictionsData(
									post.getId(),
									post.getModelType(),
									post.getSymbol(),
									post.getNumDays(),
									predictionValues);
		
		return data;
	}
}
