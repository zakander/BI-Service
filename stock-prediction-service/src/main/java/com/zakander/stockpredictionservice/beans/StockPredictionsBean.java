package com.zakander.stockpredictionservice.beans;

public class StockPredictionsBean {
	private String open;
	private String high;
	private String low;
	private String close;
	private String adjClose;
	private String volume;
	
	public StockPredictionsBean(String[] values) {
		this.open = values[0];
		this.high = values[1];
		this.low = values[2];;
		this.close = values[3];
		this.adjClose = values[4];
		this.volume = values[5];
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

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}
}
