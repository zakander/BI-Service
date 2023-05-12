package com.zakander.stockpredictionservice.predictionmodel;

import java.time.LocalDate;
import java.util.TreeMap;

import com.zakander.stockpredictionservice.scraper.Scraper;
import com.zakander.stockpredictionservice.scraper.Scraper.StockType;

public class PredictionModel {
	public static String[] predict(StockType stockType, String symbol, int numDays) {
		System.out.println(stockType + ", " + symbol + ", " + numDays);
		TreeMap<LocalDate, String[]> data = Scraper.scrape(stockType, symbol, numDays);
		
		for (LocalDate date : data.keySet()) {
			System.out.println(date + ": " + data.get(date));
		}
		return data.get(LocalDate.now());
	}
	
//	public static String SMAVal(String[] values) {
//		Double sum = 0.0;
//		
//		for (String value : values) {
//			sum += Double.parseDouble(value);
//		}
//		
//		sum /= values.length;
//		return String.format("%.2f", Double.toString(sum));
//	}
	
	public static String[] SMA(StockType stockType, String symbol, int numDays) {
		TreeMap<LocalDate, String[]> data = Scraper.scrape(stockType, symbol, numDays);
		
		Double openSum = 0.0;
		Double highSum = 0.0;
		Double lowSum = 0.0;
		Double closeSum = 0.0;
		Double adjCloseSum = 0.0;
		
		for (String[] values : data.values()) {
			openSum += Double.parseDouble(values[0]);
			highSum += Double.parseDouble(values[0]);
			lowSum += Double.parseDouble(values[0]);
			closeSum += Double.parseDouble(values[0]);
			adjCloseSum += Double.parseDouble(values[0]);
		}
		
		openSum /= numDays;
		highSum /= numDays;
		lowSum /= numDays;
		closeSum /= numDays;
		adjCloseSum /= numDays;
		
		return new String[] {Double.toString(openSum),
							Double.toString(highSum),
							Double.toString(lowSum),
							Double.toString(closeSum),
							Double.toString(adjCloseSum)};
	}
	
	public static String[] EMA(StockType stockType, String symbol, int numDays) {
		TreeMap<LocalDate, String[]> data = Scraper.scrape(stockType, symbol, numDays);
		
		Double[] EMAVals = new Double[5];
		double k = 2.0 / (numDays + 1);
		boolean firstChecked = false;
		for (String[] inputValues : data.values()) {
			
			if (!firstChecked) {
				for (int i=0; i<5; i++) {
					EMAVals[i] = Double.parseDouble(inputValues[i]);
				}
				firstChecked = true;
				continue;
			}
			
			for (int i=0; i<5; i++) {
				double currVal = Double.parseDouble(inputValues[i]);
				EMAVals[i] = currVal*k + EMAVals[i]*(1-k);
			}
			
		}
		
		String[] ret = new String[] {
				String.format("%.2f", EMAVals[0]),
				String.format("%.2f", EMAVals[1]),
				String.format("%.2f", EMAVals[2]),
				String.format("%.2f", EMAVals[3]),
				String.format("%.2f", EMAVals[4]),
		};
		
		return ret;
	}
}
