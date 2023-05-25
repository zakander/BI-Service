package com.zakander.stockpredictionservice.beans;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class DateDataPost {
	@Id
	@GeneratedValue
	private Integer id;
	
	private String dateStr;
	private String symbol;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public String getSymbol() {
		return symbol;
	}
	
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
}
