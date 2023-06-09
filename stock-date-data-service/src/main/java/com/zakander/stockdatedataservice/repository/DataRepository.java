package com.zakander.stockdatedataservice.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.zakander.stockdatedataservice.entities.StockDataRow;

@EnableScan
@Repository
public class DataRepository {
	@Autowired
	private DynamoDBMapper mapper;
	
	public StockDataRow getByKeys(String symbol, String dateStr) {
		return mapper.load(StockDataRow.class, symbol, dateStr);
	}
	
	public void save(StockDataRow row) {
		mapper.save(row);
	}
}