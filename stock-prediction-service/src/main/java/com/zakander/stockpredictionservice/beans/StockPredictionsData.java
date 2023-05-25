package com.zakander.stockpredictionservice.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class StockPredictionsData {
	@Id
	@GeneratedValue
	private Integer id;
	
	private ModelType modelType;
	private String symbol;
	private int numDays;
	private String open;
	private String high;
	private String low;
	private String close;
	private String adjClose;
	
	public enum ModelType {SMA, EMA}
	
	public StockPredictionsData(Integer id, ModelType modelType, String symbol, int numDays, String[] values) {
		this.id = id;
		this.modelType = modelType;
		this.symbol = symbol;
		this.numDays = numDays;
		this.open = values[0];
		this.high = values[1];
		this.low = values[2];;
		this.close = values[3];
		this.adjClose = values[4];
	}

	public StockPredictionsData() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ModelType getModelType() {
		return modelType;
	}

	public void setModelType(ModelType modelType) {
		this.modelType = modelType;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public int getNumDays() {
		return numDays;
	}

	public void setNumDays(int numDays) {
		this.numDays = numDays;
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
