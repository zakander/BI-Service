package com.zakander.stockscraperservice.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.zakander.stockscraperservice.scraper.entity.StockDataRow;

@Repository
public class DataRepository {
	@Autowired
	private DynamoDBMapper mapper;
	
	public void save(StockDataRow row) {
		mapper.save(row);
	}
	
	public StockDataRow getRowByKeys(String symbol, String dateStr) {
		return mapper.load(StockDataRow.class, symbol, dateStr);
	}
}
