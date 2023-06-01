package com.zakander.stockpredictionservice.repository;

import java.time.LocalDate;
import java.util.TreeMap;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.zakander.stockpredictionservice.entities.StockDataRow;

@EnableScan
@Repository
public class DataRepository {
	@Autowired
	private DynamoDBMapper mapper;
	
	public StockDataRow getByKeys(String symbol, String dateStr) {
		return mapper.load(StockDataRow.class, symbol, dateStr);
	}
	
	public TreeMap<LocalDate, String[]> getHistoricalData(String symbol, int numDays) {
		TreeMap<LocalDate, String[]> data = new TreeMap<>();
		LocalDate currentDate = LocalDate.now();
		int counter = 0;
		while (counter < numDays) {
			StockDataRow row = mapper.load(
					StockDataRow.class, symbol, currentDate.toString());
			
			if (row == null) {
				currentDate = currentDate.minusDays(1);
				continue;
			}
			
			String[] values = new String[] {
					row.getOpen(),
					row.getHigh(),
					row.getLow(),
					row.getClose(),
					row.getAdjClose()};
			data.put(currentDate, values);
			currentDate = currentDate.minusDays(1);
			if (++counter == 365) {break;}
		}
		return data;
	}
}
