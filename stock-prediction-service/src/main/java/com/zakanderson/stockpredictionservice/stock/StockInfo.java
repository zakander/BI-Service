package com.zakanderson.stockpredictionservice.stock;

import java.io.IOException;
import java.math.BigDecimal;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

public class StockInfo {
	private String name;
	private BigDecimal price;
	private BigDecimal change;
	private BigDecimal peg;
	private BigDecimal dividend;
	
	public StockInfo(String name) throws IOException {
		this.name = name;
		Stock stock = YahooFinance.get(name);
		this.price = stock.getQuote().getPrice();
		this.change = stock.getQuote().getChangeInPercent();
		this.peg = stock.getStats().getPeg();
		this.dividend = stock.getDividend().getAnnualYieldPercent();
	}

	@Override
	public String toString() {
		return "StockInfo [name=" + name + ", price=" + price + ", change=" + change + ", peg=" + peg + ", dividend="
				+ dividend + "]";
	}
}
