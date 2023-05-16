package com.zakander.stockpredictionservice.beans;

public class StockPredictionsBean {
	private String stockSymbol;
	private String open;
	private String high;
	private String low;
	private String close;
	private String adjClose;
	
	public StockPredictionsBean(String[] values) {
		this.stockSymbol = values[0];
		this.open = values[1];
		this.high = values[2];
		this.low = values[3];;
		this.close = values[4];
		this.adjClose = values[5];
	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
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
