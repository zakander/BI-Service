package com.zakander.stockscraperservice.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="stock_data")
public class StockDataRow {
	private String stockSymbol;
	private String dateStr;
	
	private String open;
	private String high;
	private String low;
	private String close;
	private String adjClose;
	
	@DynamoDBHashKey(attributeName="stock_symbol")
	public String getStockSymbol() {
		return stockSymbol;
	}
	
	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}
	
	@DynamoDBRangeKey(attributeName="date_str")
	public String getDateStr() {
		return dateStr;
	}
	
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	
	public String getOpen() {
		return open;
	}
	
	public void setOpen(String open) {
		this.open = open;
	}
	
	public String getHigh() {
		return high;
	}
	
	public void setHigh(String high) {
		this.high = high;
	}
	
	public String getLow() {
		return low;
	}
	
	public void setLow(String low) {
		this.low = low;
	}
	
	public String getClose() {
		return close;
	}
	
	public void setClose(String close) {
		this.close = close;
	}
	
	public String getAdjClose() {
		return adjClose;
	}
	
	public void setAdjClose(String adjClose) {
		this.adjClose = adjClose;
	}
}
