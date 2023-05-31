package com.zakander.stockscraperservice.scraper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.TreeMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.zakander.stockscraperservice.entities.StockDataRow;

/*
 * The scraper class utilises the jsoup library to scrape real-time historical
 * stock price data from YahooFinance. Currently, this class is currently only
 * able to scrape data from three types of stocks: indices, currencies and
 * cryptocurrencies. The scraping function below is used by the PredictionModel
 * class to calculate the SMA and EMA of each data attribute, namely the open,
 * high, low, close and adjusted closing prices.
 */
public class Scraper {
	@Autowired
	private static DynamoDBMapper mapper;
	
	private static final String BASE_URL = "https://au.finance.yahoo.com/quote/";
	private static final int NUM_STOCKS = 10;
	public enum StockType {
		// Used to format the link for online historical data
		CRYPTO,
		INDEX,
		CURRENCY
	}
	
	public static StockType getStockType(String symbol) {
		/*
		 * Special characters in input string for stock indicate whether
		 * it is an index, currency or cryptocurrency.
		 * For instance, all symbols for index stocks are preceeded by a
		 * caret (^), e.g. ^AXJO represents S&P/ASX 200 index.
		 */
		if (symbol.charAt(0) == '^') {
			return StockType.INDEX;
		}
		if (symbol.contains("=X")) {
			return StockType.CURRENCY;
		}
		return StockType.CRYPTO;
	}
	
	public static boolean dateMatches(String dateFormat1, String dateFormat2) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
		LocalDate date1 = LocalDate.parse(dateFormat1, formatter);
		LocalDate date2 = LocalDate.parse(dateFormat2);
		return date1.equals(date2);
	}
	
	public static void scrapeSymbols() {
		File f = new File("stockSymbols.txt");
		try {
			FileWriter writer = new FileWriter(f);
			// index url
			String url = "https://au.finance.yahoo.com/world-indices/";
			Document doc = (Document) Jsoup.connect(url).get();
			int i = 0;
			for (Element row : doc.select("table.W\\(100\\%\\) tr")) {
				writer.write(row.select("td:nth-of-type(1)").text());
				if (i++ == NUM_STOCKS) {break;}
			}
			
			// currency url
			url = "https://au.finance.yahoo.com/currencies/";
			doc = (Document) Jsoup.connect(url).get();
			i = 0;
			for (Element row : doc.select("table.W\\(100\\%\\) tr")) {
				writer.write(row.select("td:nth-of-type(1)").text());
				if (i++ == NUM_STOCKS) {break;}
			}
			
			// crypto url
			url = "https://au.finance.yahoo.com/crypto/";
			doc = (Document) Jsoup.connect(url).get();
			i = 0;
			for (Element row : doc.select("table.W\\(100\\%\\) tr")) {
				writer.write(row.select("td:nth-of-type(1)").text());
				if (i++ == NUM_STOCKS) {break;}
			}
			
			writer.close();
		}
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String[] scrapeDate(String symbol, String dateStr) {
		// dateStr is in format yyyy-mm-dd
		String url = BASE_URL;
		
		StockType stockType = getStockType(symbol);
		
		// Each stock type has a unique link structure extending from base URL.
		switch (stockType) {
			case CRYPTO:
				url = (url + "<F>/history?p=<F>").replaceAll("<F>", symbol);
				break;
			case INDEX:
				String newSymb = symbol.replaceAll("\\^", "");
				url = (url + "%5E<F>/history?p=%5E<F>").replaceAll("<F>", newSymb);
				break;
			case CURRENCY:
				String newSymb1 = symbol.replaceAll("=X", "");
				url = (url + "<F>%3DX/history?p=<F>%3DX").replaceAll("<F>", newSymb1);
				break;
			default:
				break;
		}
		
		try {
			final Document doc = (Document) Jsoup.connect(url).get();
			
			for (Element row : doc.select("table.W\\(100\\%\\) tr")) {
				
				// If row contains no information, we skip over it.
				if (row.select("td:nth-of-type(1)").text().equals("")) {
					continue;
				}
				
				if (dateMatches(row.select("td:nth-of-type(1)").text(), dateStr)) {
					String open = row.select("td:nth-of-type(2)").text();
					
					/* Some rows (typically current or previous date) may
					 * contain no stock data yet, and are represented
					 * with a hyphen ("-") symbol. */
					if (open.equals("-")) {
//						String[] ret = new String[5];
//						Arrays.fill(ret, "Data not available yet");
//						ret[0] = symbol;
						return null;
					}
					
					String high = row.select("td:nth-of-type(3)").text();
					String low = row.select("td:nth-of-type(4)").text();
					String close = row.select("td:nth-of-type(5)").text();
					String adjClose = row.select("td:nth-of-type(6)").text();
					
					/* Stock prices are represented with commas for readability,
					 * which are removed below for numerical calculation purposes. */
					open = open.replaceAll(",", "");
					high = high.replaceAll(",", "");
					low = low.replaceAll(",", "");
					close = close.replaceAll(",", "");
					adjClose = adjClose.replaceAll(",", "");
					
					return new String[] {open, high, low, close, adjClose};
				}
			}
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static TreeMap<LocalDate, String[]> scrapeHistory(String symbol, int numDays) {
		/*
		 * This method scrapes historical stock price data from YahooFinance.
		 * There are five data attributes read from the database:
		 * the open, high, low, close and adjusted close prices.
		 */
		
		// First part of data source URL is the same for all stocks
		String url = BASE_URL;
		
		StockType stockType = getStockType(symbol);
		
		// Each stock type has a unique link structure extending from base URL.
		switch (stockType) {
			case CRYPTO:
				url = (url + "<F>/history?p=<F>").replaceAll("<F>", symbol);
				break;
			case INDEX:
				String newSymb = symbol.replaceAll("\\^", "");
				url = (url + "%5E<F>/history?p=%5E<F>").replaceAll("<F>", newSymb);
				break;
			case CURRENCY:
				String newSymb1 = symbol.replaceAll("=X", "");
				url = (url + "<F>%3DX/history?p=<F>%3DX").replaceAll("<F>", newSymb1);
				break;
			default:
				break;
		}
		
		TreeMap<LocalDate, String[]> dB = new TreeMap<>();
		
		try {
			final Document doc = (Document) Jsoup.connect(url).get();
			int counter = 0;
			for (Element row : doc.select("table.W\\(100\\%\\) tr")) {
				
				// If row contains no information, we skip over it.
				if (row.select("td:nth-of-type(1)").text().equals("")) {
					continue;
				}
				
				String dateStr = row.select("td:nth-of-type(1)").text();
				String open = row.select("td:nth-of-type(2)").text();
				
				/* Some rows (typically current or previous date) may
				 * contain no current stock data, and are represented
				 * with a hyphen ("-") symbol. */
				if (open.equals("-")) {
					continue;
				}
				
				String high = row.select("td:nth-of-type(3)").text();
				String low = row.select("td:nth-of-type(4)").text();
				String close = row.select("td:nth-of-type(5)").text();
				String adjClose = row.select("td:nth-of-type(6)").text();
				
				/* Stock prices are represented with commas for readability,
				 * which are removed below for numerical calculation purposes. */
				open = open.replaceAll(",", "");
				high = high.replaceAll(",", "");
				low = low.replaceAll(",", "");
				close = close.replaceAll(",", "");
				adjClose = adjClose.replaceAll(",", "");
				
				/* Date of stock price data on YahooFinance is always written
				 * in the format dd MMM yyyy (e.g. 06 May 2023).
				 * The two lines below make a date object from parsing
				 * the date string in this format. */
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
				LocalDate date = LocalDate.parse(dateStr, formatter);
				
//				String[] values = new String[] {open, high, low, close, adjClose};
				dB.put(date, new String[] {open, high, low, close, adjClose});
				
				if (++counter == numDays) {break;}
			}
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return dB;
	}
	
	public static void scrapeAll() {
		File f = new File("stockSymbols.txt");
		try {
			Scanner sc = new Scanner(f);
			while (sc.hasNextLine()) {
				String symbol = sc.nextLine();
				TreeMap<LocalDate, String[]> dB = scrapeHistory(symbol, 365);
				for (LocalDate date : dB.keySet()) {
					String[] values = dB.get(date);
					mapper.save(new StockDataRow(symbol, date.toString(),
							values[0], values[1], values[2], values[3], values[4]));
				}
			}
			sc.close();
		}
		
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
