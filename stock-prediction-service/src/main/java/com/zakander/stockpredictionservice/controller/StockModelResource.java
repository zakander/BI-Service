package com.zakander.stockpredictionservice.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.zakander.stockpredictionservice.beans.DateDataPost;
import com.zakander.stockpredictionservice.beans.PredictionsPost;
import com.zakander.stockpredictionservice.beans.StockDateData;
import com.zakander.stockpredictionservice.beans.StockPredictionsData;
import com.zakander.stockpredictionservice.jpa.StockDateDataRepository;
import com.zakander.stockpredictionservice.jpa.StockPredictionsDataRepository;
import com.zakander.stockpredictionservice.predictionmodel.PredictionModel;
import com.zakander.stockpredictionservice.scraper.Scraper;

import jakarta.validation.Valid;

@RestController
public class StockModelResource {
	
	private StockDateDataRepository dateRepository;
	private StockPredictionsDataRepository predictionsRepository;
	
	public StockModelResource(
			StockDateDataRepository dateRepository,
			StockPredictionsDataRepository predictionsRepository) {
		this.dateRepository = dateRepository;
		this.predictionsRepository = predictionsRepository;
	}

	@GetMapping("/stock-date-data/all-posts")
	public List<StockDateData> retrieveAllDateData() {
		return dateRepository.findAll();
	}
	
	@GetMapping("/stock-prediction/all-posts")
	public List<StockPredictionsData> retrieveAllPredictions() {
		return predictionsRepository.findAll();
	}
	
	@GetMapping("/stock-date-data/posts/{symbol}/{dateStr}")
	public EntityModel<StockDateData> retrieveDateData(
			@PathVariable String symbol,
			@PathVariable String dateStr) {
		
		Optional<StockDateData> data = Optional.of(
							dateRepository.findBySymbolAndDateStr(symbol, dateStr));
		
		EntityModel<StockDateData> entityModel = EntityModel.of(data.get());
		
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass())
										.retrieveAllPredictions());
		entityModel.add(link.withRel("all-stockpredictions"));
		
		return entityModel;
	}
	
	@GetMapping("/stock-prediction/posts/{symbol}/{numDays}")
	public EntityModel<StockPredictionsData> retrievePredictionsData(
			@PathVariable String symbol,
			@PathVariable int numDays) {
		
		Optional<StockPredictionsData> data = Optional.of(
				predictionsRepository.findBySymbolAndNumDays(symbol, numDays));
		
		EntityModel<StockPredictionsData> entityModel = EntityModel.of(data.get());
		
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass())
										.retrieveAllPredictions());
		entityModel.add(link.withRel("all-stockpredictions"));
		
		return entityModel;
	}
	
	@DeleteMapping("/stock-date-data/posts/{symbol}/{dateStr}")
	public void deleteDateData(
			@PathVariable String symbol,
			@PathVariable String dateStr) {
		
		StockDateData data = dateRepository.findBySymbolAndDateStr(symbol, dateStr);
		dateRepository.delete(data);
	}
	
	@DeleteMapping("/stock-prediction/posts/{symbol}/{numDays}")
	public void deletePredictions(
			@PathVariable String symbol,
			@PathVariable int numDays) {
		
		predictionsRepository.deleteBySymbolAndNumDays(symbol, numDays);
	}
	
	@PostMapping("/stock-date-data/posts")
	public ResponseEntity<Object> createDateData(@Valid @RequestBody DateDataPost post) {
		
		String[] values = Scraper.scrapeDate(
									post.getSymbol(),
									post.getDateStr());
		assert values != null;
		
		StockDateData data = new StockDateData(
									post.getId(),
									post.getDateStr(),
									post.getSymbol(),
									values);
		
		dateRepository.saveAndFlush(data);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(data.getId())
						.toUri();
		
		return ResponseEntity.created(location).build();
	}

	@PostMapping("/stock-prediction/posts")
	public ResponseEntity<Object> createPredictions(@Valid @RequestBody PredictionsPost post) {
		
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
		
		predictionsRepository.saveAndFlush(data);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(data.getId())
						.toUri();
		
		return ResponseEntity.created(location).build();
	}
}
