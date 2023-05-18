package com.zakander.stockpredictionservice.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.zakander.stockpredictionservice.beans.PredictionsPost;
import com.zakander.stockpredictionservice.beans.StockDateData;
import com.zakander.stockpredictionservice.beans.StockPredictionsData;
import com.zakander.stockpredictionservice.jpa.PostRepository;
import com.zakander.stockpredictionservice.jpa.StockDateDataRepository;
import com.zakander.stockpredictionservice.jpa.StockPredictionsDataRepository;
import com.zakander.stockpredictionservice.predictionmodel.PredictionModel;

import jakarta.validation.Valid;

@RestController
public class StockModelResource {
	
	private StockDateDataRepository dateRepository;
	private StockPredictionsDataRepository predictionsRepository;
	private PostRepository postRepository;
	
	public StockModelResource(
			StockDateDataRepository dateRepository,
			StockPredictionsDataRepository predictionsRepository,
			PostRepository postRepository) {
		this.dateRepository = dateRepository;
		this.predictionsRepository = predictionsRepository;
		this.postRepository = postRepository;
	}


	@GetMapping("/stock-prediction/all-posts/date")
	public List<StockDateData> retrieveAllDateData() {
		return dateRepository.findAll();
	}
	
	@GetMapping("/stock-prediction/all-posts/models")
	public List<StockPredictionsData> retrieveAllPredictions() {
		return predictionsRepository.findAll();
	}
	
	@GetMapping("/stock-date-data/data/{symbol}/{date}")
	public EntityModel<StockDateData> retrieveDateData(
											@PathVariable String symbol,
											@PathVariable LocalDate date) {
		Optional<StockDateData> data = Optional.of(dateRepository.findBySymbolAndDate(symbol, date));
		
		EntityModel<StockDateData> entityModel = EntityModel.of(data.get());
		
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllPredictions());
		entityModel.add(link.withRel("all-stockpredictions"));
		
		return entityModel;
	}
	
	@GetMapping("/stock-prediction/data/{symbol}/{numDays}")
	public EntityModel<StockPredictionsData> retrievePredictionsData(
											@PathVariable String symbol,
											@PathVariable int numDays) {
		Optional<StockPredictionsData> data = Optional.of(predictionsRepository.findBySymbolAndNumDays(symbol, numDays));
		
		EntityModel<StockPredictionsData> entityModel = EntityModel.of(data.get());
		
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllPredictions());
		entityModel.add(link.withRel("all-stockpredictions"));
		
		return entityModel;
	}

	@PostMapping("stock-prediction/posts")
	public ResponseEntity<Object> createPredictions(@Valid @RequestBody PredictionsPost post) {
		
//		System.out.println("\n----\n");
//		System.out.println(post.getSymbol());
//		System.out.println(post.getNumDays());
//		System.out.println(post.getModelType());
		System.out.println("\n----\n");
		String[] values = PredictionModel.getPredictions(post.getSymbol(), post.getNumDays(), post.getModelType());
		StockPredictionsData data = new StockPredictionsData(post.getId(), post.getModelType(), post.getSymbol(), post.getNumDays(), values);
//		EntityModel<StockPredictionsData> dataEntity = EntityModel.of(data);
		predictionsRepository.saveAndFlush(data);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(data.getId())
						.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
//	@GetMapping("/date-data/{symbol}/{dateStr}")
//	public StockDateData retrieveData(
//			@PathVariable String symbol,
//			@PathVariable String dateStr) {
//		
//		String[] data = Scraper.scrapeDate(symbol, dateStr);
//		return new StockDateData(data);
//	}
}
