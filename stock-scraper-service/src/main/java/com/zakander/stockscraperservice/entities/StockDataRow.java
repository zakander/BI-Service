package com.zakander.stockscraperservice.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "stock_data")
public class StockDataRow {
	private String stockSymbol;
	private String dateStr;
	
	private String open;
	private String high;
	private String low;
	private String close;
	private String adjClose;
	
	public StockDataRow() {}

	public StockDataRow(String stockSymbol, String dateStr, String open, String high, String low, String close,
			String adjClose) {
		this.stockSymbol = stockSymbol;
		this.dateStr = dateStr;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.adjClose = adjClose;
	}

	@DynamoDBHashKey(attributeName = "stock_symbol")
	public String getStockSymbol() {
		return stockSymbol;
	}
	
	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}
	
	@DynamoDBRangeKey(attributeName = "date_str")
	public String getDateStr() {
		return dateStr;
	}
	
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	
	@DynamoDBAttribute
	public String getOpen() {
		return open;
	}
	
	public void setOpen(String open) {
		this.open = open;
	}
	
	@DynamoDBAttribute
	public String getHigh() {
		return high;
	}
	
	public void setHigh(String high) {
		this.high = high;
	}
	
	@DynamoDBAttribute
	public String getLow() {
		return low;
	}
	
	public void setLow(String low) {
		this.low = low;
	}
	
	@DynamoDBAttribute
	public String getClose() {
		return close;
	}
	
	public void setClose(String close) {
		this.close = close;
	}
	
	@DynamoDBAttribute
	public String getAdjClose() {
		return adjClose;
	}
	
	public void setAdjClose(String adjClose) {
		this.adjClose = adjClose;
	}
}
