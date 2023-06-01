package com.zakander.stockscraperservice.controller;

import java.time.LocalDate;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zakander.stockscraperservice.entities.StockDataRow;
import com.zakander.stockscraperservice.repository.DataRepository;
import com.zakander.stockscraperservice.scraper.Scraper;
import com.zakander.stockscraperservice.scraper.StockSymbols;

@RestController
public class Controller {
	@Autowired
	private DataRepository repository;
	
	@PostMapping("/scrape")
	public void scrape() {
		for (String symbol : StockSymbols.SYMBOLS) {
			TreeMap<LocalDate, String[]> dB = Scraper.scrapeHistory(symbol, Scraper.NUM_STOCKS);
			for (LocalDate date : dB.keySet()) {
				String[] values = dB.get(date);
				repository.save(new StockDataRow(symbol, date.toString(),
						values[0], values[1], values[2], values[3], values[4]));
			}
		}
	}
}
