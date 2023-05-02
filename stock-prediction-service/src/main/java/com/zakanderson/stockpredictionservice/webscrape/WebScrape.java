package com.zakanderson.stockpredictionservice.webscrape;

import java.math.BigDecimal;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

public class WebScrape {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			Stock stock = YahooFinance.get("INTL");
			
			System.out.println(stock.getQuote());

//			BigDecimal price = stock.getQuote().getPrice();
//			BigDecimal change = stock.getQuote().getChangeInPercent();
//			BigDecimal peg = stock.getStats().getPeg();
//			BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();
//
//			stock.print();
//			System.out.println();
//			System.out.println("Price: " + price);
//			System.out.println("Change: " + change);
//			System.out.println("Peg: " + peg);
//			System.out.println("Dividend: " + dividend);
		}
		
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}