package com.zakander.stockdatedataservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zakander.stockdatedataservice.entities.Post;
import com.zakander.stockdatedataservice.entities.StockDataRow;
import com.zakander.stockdatedataservice.repository.DataRepository;
import com.zakander.stockdatedataservice.scraper.Scraper;

@RestController
public class Controller {
	@Autowired
	private DataRepository repository;
	
	@GetMapping("/get/{stockSymbol}/{dateStr}")
	public StockDataRow geteStockDataRowByKeys(
			@PathVariable("stockSymbol") String symbol,
			@PathVariable("dateStr") String dateStr) {
		StockDataRow row = repository.findBySymbolAndDate(symbol, dateStr);
		assert row != null;
		return row;
	}
	
	@DeleteMapping("/delete/{stockSymbol}/{dateStr}")
	public void deleteDateData(
			@PathVariable("stockSymbol") String symbol,
			@PathVariable("dateStr") String dateStr) {
		
		StockDataRow row = repository.findBySymbolAndDate(symbol, dateStr);
		repository.delete(row);
	}
	
	@PostMapping("/post")
	public void saveStockDataRow(@RequestBody Post post) {
		String[] values = Scraper.scrapeDate(post.getStockSymbol(), post.getDateStr());
		StockDataRow row = new StockDataRow(post.getStockSymbol(), post.getDateStr(),
				values[0], values[1], values[2], values[3], values[4]);
		repository.save(row);
	}
}
