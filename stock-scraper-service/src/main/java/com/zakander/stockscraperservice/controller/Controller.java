package com.zakander.stockscraperservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zakander.stockscraperservice.entities.Post;
import com.zakander.stockscraperservice.entities.StockDataRow;
import com.zakander.stockscraperservice.repository.IDataRepository;
import com.zakander.stockscraperservice.scraper.Scraper;

@RestController
public class Controller {
	@Autowired
	private IDataRepository repository;
	
	@GetMapping("/row/{stockSymbol}/{dateStr}")
	public StockDataRow geteStockDataRowByKeys(
			@PathVariable("stockSymbol") String symbol,
			@PathVariable("dateStr") String dateStr) {
		StockDataRow row = repository.findByStockSymbolAndDateStr(symbol, dateStr);
		assert row != null;
		return row;
	}
	
	@PostMapping("/row")
	public void saveStockDataRow(@RequestBody Post post) {
		String[] values = Scraper.scrapeDate(post.getStockSymbol(), post.getDateStr());
		StockDataRow row = new StockDataRow(post.getStockSymbol(), post.getDateStr(),
				values[0], values[1], values[2], values[3], values[4]);
		repository.save(row);
	}
}
