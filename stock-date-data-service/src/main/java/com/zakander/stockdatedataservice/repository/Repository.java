package com.zakander.stockdatedataservice.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.zakander.stockscraperservice.entities.StockDataRow;

@EnableScan
@Repository
public class Repository {
	@Autowired
	private DynamoDBMapper mapper;
	
	public StockDataRow findById(Integer id) {
		return mapper.load(StockDataRow.class, id);
	}
	
	public StockDataRow findBySymbolAndDate(String symbol, String dateStr) {
		return mapper.load(StockDataRow.class, symbol, dateStr);
	}
	
	public void save(StockDataRow row) {
		mapper.save(row);
	}
}