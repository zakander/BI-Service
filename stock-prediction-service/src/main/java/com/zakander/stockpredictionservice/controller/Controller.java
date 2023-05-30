package com.zakander.stockpredictionservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zakander.stockpredictionservice.entities.Post;
import com.zakander.stockpredictionservice.entities.StockPredictionsData;
import com.zakander.stockpredictionservice.predictionmodel.PredictionModel;

import jakarta.validation.Valid;

@RestController
public class Controller {
	@PostMapping("/make-prediction")
	public StockPredictionsData createPredictions(@Valid @RequestBody Post post) {
		
		String[] values = PredictionModel.getPredictions(
									post.getSymbol(),
									post.getNumDays(),
									post.getModelType());
		
		StockPredictionsData data = new StockPredictionsData(
									post.getId(),
									post.getModelType(),
									post.getSymbol(),
									post.getNumDays(),
									values);
		
		return data;
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//						.path("/{id}")
//						.buildAndExpand(data.getId())
//						.toUri();
//		
//		return ResponseEntity.created(location).build();
	}
}
