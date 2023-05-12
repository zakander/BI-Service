package com.zakander.stockpredictionservice.scraper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.TreeMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Scraper {
	private static final String BASE_URL = "https://au.finance.yahoo.com/quote/";
	private static LocalDate latestDate = LocalDate.now();
	
	public enum StockType {
		CRYPTO,
		INDEX,
		CURRENCY;
	}
	
	public static TreeMap<LocalDate, String[]> scrape(StockType stockType, String symbol, int numDays) {
		
		String url = BASE_URL;
		
		switch (stockType) {
			case CRYPTO:
				url = (url + "<F>?p=<F>").replaceAll("<F>", symbol);
				break;
			case INDEX:
				url = (url + "%5E<F>/history?p=%5E<F>").replaceAll("<F>", symbol);
				break;
			case CURRENCY:
				symbol = symbol.replaceAll("=X", "");
				url = (url + "<F>%3DX?p=<F>%3DX").replaceAll("<F>", symbol);
				break;
			default:
				break;
		}
		
		TreeMap<LocalDate, String[]> dB = new TreeMap<>();
		
		try {
			final Document doc = (Document) Jsoup.connect(url).get();
			
//			String name = (doc.select("div.D\\(iB\\) h1").text());
			
			int counter = 0;
			
			boolean firstRow = false;
			for (Element row : doc.select("table.W\\(100\\%\\) tr")) {
				
				if (row.select("td:nth-of-type(1)").text().equals("")) {
					continue;
				}
				if (!firstRow) {
					firstRow = true;
					continue;
				}
				
				String dateStr = row.select("td:nth-of-type(1)").text();
				
				String open = row.select("td:nth-of-type(2)").text();
				String high = row.select("td:nth-of-type(3)").text();
				String low = row.select("td:nth-of-type(4)").text();
				String close = row.select("td:nth-of-type(5)").text();
				String adjClose = row.select("td:nth-of-type(6)").text();
				String volume = row.select("td:nth-of-type(7)").text();
				
				open = open.replaceAll(",", "");
				high = high.replaceAll(",", "");
				low = low.replaceAll(",", "");
				close = close.replaceAll(",", "");
				adjClose = adjClose.replaceAll(",", "");
				volume = volume.replaceAll(",", "");
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
				LocalDate date = LocalDate.parse(dateStr, formatter);
				
				String[] values = new String[] {open, high, low, close, adjClose, volume};
				dB.put(date, values);
				
				if (++counter == numDays) {break;}
			}
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return dB;
	}
}
