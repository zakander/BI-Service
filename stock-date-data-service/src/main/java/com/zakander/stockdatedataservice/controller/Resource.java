package com.zakander.stockdatedataservice.controller;

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

import com.zakander.stockdatedataservice.beans.Post;
import com.zakander.stockdatedataservice.beans.StockDateData;
import com.zakander.stockdatedataservice.repository.Repository;
import com.zakander.stockscraperservice.scraper.Scraper;

import jakarta.validation.Valid;

@RestController
public class Resource {
	private Repository repository;
	
	public Resource(Repository repository) {
		this.repository = repository;
	}

	@GetMapping("/stock-date-data/all-posts")
	public List<StockDateData> retrieveAllDateData() {
		return repository.findAll();
	}
	
	@GetMapping("/stock-date-data/posts/{symbol}/{dateStr}")
	public EntityModel<StockDateData> retrieveDateData(
			@PathVariable String symbol,
			@PathVariable String dateStr) {
		
		Optional<StockDateData> data = Optional.of(
							repository.findBySymbolAndDateStr(symbol, dateStr));
		
		EntityModel<StockDateData> entityModel = EntityModel.of(data.get());
		
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass())
										.retrieveAllDateData());
		entityModel.add(link.withRel("all-stockpredictions"));
		
		return entityModel;
	}
	
	@DeleteMapping("/stock-date-data/posts/{symbol}/{dateStr}")
	public void deleteDateData(
			@PathVariable String symbol,
			@PathVariable String dateStr) {
		
		StockDateData data = repository.findBySymbolAndDateStr(symbol, dateStr);
		repository.delete(data);
	}
	
	@PostMapping("/stock-date-data/posts")
	public ResponseEntity<Object> createDateData(@Valid @RequestBody Post post) {
		
		String[] values = Scraper.scrapeDate(
									post.getSymbol(),
									post.getDateStr());
		assert values != null;
		
		StockDateData data = new StockDateData(
									post.getId(),
									post.getDateStr(),
									post.getSymbol(),
									values);
		
		repository.saveAndFlush(data);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(data.getId())
						.toUri();
		
		return ResponseEntity.created(location).build();
	}
}
