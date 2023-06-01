package com.zakander.stockdatedataservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.zakander.stockdatedataservice.entities.StockDataRow;
import com.zakander.stockdatedataservice.repository.DataRepository;

@RestController
public class Controller {
	@Autowired
	private DataRepository repository;
	
	@GetMapping("/get/{stockSymbol}/{dateStr}")
	public StockDataRow geteStockDataRowByKeys(
			@PathVariable("stockSymbol") String symbol,
			@PathVariable("dateStr") String dateStr) {
		StockDataRow row = repository.getByKeys(symbol, dateStr);
		assert row != null;
		return row;
	}
}
