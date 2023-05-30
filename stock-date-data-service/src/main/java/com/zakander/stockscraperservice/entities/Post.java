package com.zakander.stockscraperservice.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class Post {
	@Id
	@GeneratedValue
	private Integer id;
	
	private String stockSymbol;
	private String dateStr;
	
	public String getStockSymbol() {
		return stockSymbol;
	}
	
	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}
	
	public String getDateStr() {
		return dateStr;
	}
	
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
}
