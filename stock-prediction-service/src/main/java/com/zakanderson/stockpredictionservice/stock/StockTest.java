package com.zakanderson.stockpredictionservice.stock;

import java.io.IOException;

public class StockTest {

	public static void main(String[] args) throws IOException {
		StockInfo stock1 = new StockInfo("GC=F");
		
		System.out.println(stock1.toString());
	}

}
