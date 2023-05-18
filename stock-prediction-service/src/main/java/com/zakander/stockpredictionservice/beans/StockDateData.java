package com.zakander.stockpredictionservice.beans;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class StockDateData {
	@Id
	@GeneratedValue
	private Integer id;
	
	private LocalDate date;
	private String symbol;
	private String open;
	private String high;
	private String low;
	private String close;
	private String adjClose;
	
	public StockDateData(Integer id, LocalDate date, String symbol, String[] values) {
		this.id = id;
		this.date = date;
		this.symbol = symbol;
		this.open = values[0];
		this.high = values[1];
		this.low = values[2];;
		this.close = values[3];
		this.adjClose = values[4];
	}
	
	public StockDateData() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
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
