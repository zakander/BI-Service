package com.zakander.stockscraperservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zakander.stockscraperservice.repository.DataRepository;
import com.zakander.stockscraperservice.scraper.entity.StockDataRow;

@RestController
public class Controller {
	@Autowired
	private DataRepository repository;
	
	@GetMapping("/row/{stockSymbol}/{dateStr}")
	public StockDataRow geteStockDataRowByKeys(
			@PathVariable("stockSymbol") String symbol,
			@PathVariable("dateStr") String dateStr) {
		StockDataRow row = repository.getRowByKeys(symbol, dateStr);
		assert row != null;
		return row;
	}
	
	@PostMapping("/row")
	public void saveStockDataRow(@RequestBody StockDataRow row) {
		repository.save(row);
	}
}
